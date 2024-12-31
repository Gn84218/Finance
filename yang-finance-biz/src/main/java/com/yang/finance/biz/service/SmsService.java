package com.yang.finance.biz.service;

public interface SmsService {
    /**
     * 发送短信验证码
     * @param phone
     * @param smsCode
     * @param smsCodeType
     */
    void sendSmsCode(String phone, String smsCode, String smsCodeType);

}