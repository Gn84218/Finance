package com.yang.finance.biz.service.impl;

import com.yang.finance.biz.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    /**
     * 发送短信验证码
     *
     * @param phone
     * @param smsCode
     * @param smsCodeType
     */
    @Override
    public void sendSmsCode(String phone, String smsCode, String smsCodeType) {

    }


}
