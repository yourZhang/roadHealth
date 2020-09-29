package cn.zys.service;

public interface SmsAliService {

    //发送短信并存入redis
    Boolean sendSms(String validateCodeType, String phone);

    //取出验证码并核验
    Boolean checkValidateCode(String validateCodeType,String phone,String code);
}
