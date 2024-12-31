package com.yang.finance.biz.service;


//import com.yang.finance.biz.dto.form.GetBase64CodeForm;
import com.yang.common.dto.TokenResponse;
import com.yang.finance.biz.dto.form.*;

public interface MemberLoginService {
    /**
     * 取得客户ID
     * @return
     */
    String getClientId();

    /**
     * 获取图形验证码
     * @param
     * @return
     */
    String getBase64Code(GetBase64CodeForm form);
    /**
     * 获取短信验证码
     *
     * @param form
     * @return
     */
    void  sendSmsCode(GetSmsCodeForm form);
    /**
     * 校验图形验证码
     *
     * @param clientId
     * @return
     */
    boolean checkBase64Code(String clientId, String code);

    /**
     * 校验短信验证码
     *
     * @param phone
     * @param smsCode
     * @param smsCodeType
     * @return
     */
    boolean checkSmsCode(String phone, String smsCode, String smsCodeType);

    /**
     * 手机账号密码登录
     *
     * @param form
     * @return
     */
    TokenResponse phonePasswordLogin(PhonePasswordLoginForm form);

    /**
     * 手机短信登录
     *
     * @param form
     * @return
     */
    TokenResponse phoneSmsCodeLogin(PhoneSmsCodeLoginForm form);

    TokenResponse getClientToken(String clientId);
}
