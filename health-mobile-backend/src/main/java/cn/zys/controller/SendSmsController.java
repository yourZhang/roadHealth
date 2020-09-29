package cn.zys.controller;

import cn.zys.common.MessageConst;
import cn.zys.entity.Result;
import cn.zys.service.SmsAliService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: road-health
 * @description: 短信业务
 * @author: xiaozhang6666
 * @create: 2020-09-29 15:47
 **/
@RestController
@RequestMapping("cwap/sendSms")
@Slf4j
public class SendSmsController {

    @Reference
    SmsAliService smsAliService;

    @RequestMapping("setCode")
    public Result setCode(String type, String telephone) {
        log.info("参数打印{}:{}", type, telephone);
        final Boolean aBoolean = smsAliService.sendSms(type, telephone);
        return new Result(true, MessageConst.SEND_VALIDATECODE_SUCCESS);
    }
}
