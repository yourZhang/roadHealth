package cn.zys.quartzs;

import cn.zys.common.RedisMessage;
import cn.zys.utils.QiniuUtils;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-25 21:19
 **/
@Slf4j
public class ClearImageJob {
    /**
     * 定义清理图片的任务
     */

    @Autowired
    JedisPool jedisPool;

    public void clearImagesJob() {
        System.out.println("clearImageJob......");
        try (final Jedis resource = jedisPool.getResource()) {
            final Set<String> sdiff = resource.sdiff(RedisMessage.ImageOutRedisName, RedisMessage.ImageInRedisName);
            sdiff.forEach((value) -> {
                try {
                    QiniuUtils.deleteFileFromQiniu(value);
                } catch (QiniuException e) {
                    log.info("七牛云清除图片异常~~~");
                    e.printStackTrace();
                }
                //从redis移除
                resource.srem(RedisMessage.ImageOutRedisName, value);
            });
        }
    }
}
