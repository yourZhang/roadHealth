package cn.zys.service.impl;

import cn.zys.common.RedisMessage;
import cn.zys.service.SmsAliService;
import cn.zys.utils.SmsUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.security.SecureRandom;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-29 15:45
 **/
@Service
@Slf4j
public class SmsAliServiceImpl implements SmsAliService {

    @Autowired
    JedisPool jedisPool;

    @Override
    public Boolean sendSms(String validateCodeType, String phone) {
        String signName = "ABC商城";
        String templateCode = "SMS_200175394";
        Integer code = (int) (1000 + new SecureRandom().nextDouble() * 8999);
        //随机数
        log.debug("验证码是{}", code);
        String param = "{\"code\":\"" + code + "\"}";
//        try {
//            SmsUtils.sendSms(phone, signName, templateCode, param);
//        } catch (ClientException e) {
//            log.error("阿里云短信错误{}", e.getMessage());
//        }
        //缓存到redis
        //得到key
        String redisKey = RedisMessage.VALIDATE_CODE_PREFIX + validateCodeType + ":" + phone;
        try (Jedis resource = jedisPool.getResource();) {
            resource.setex(redisKey, 60 * 60, String.valueOf(code));
        }
        return true;
    }

    @Override
    public Boolean checkValidateCode(String validateCodeType, String phone, String code) {
        //拼接key值
        String redisKey = RedisMessage.VALIDATE_CODE_PREFIX + validateCodeType + ":" + phone;
        try (final Jedis resource = jedisPool.getResource()) {
            final String s = resource.get(redisKey);
            //对比是否匹配
            if (!StringUtils.isEmpty(s) && s.equals(code)) {
                //如果匹配则删除redis并返回true
//                resource.del(redisKey);
                return true;
            } else {
                //如果不匹配则返回false
                return false;
            }
        }
    }
}
