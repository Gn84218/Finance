package com.yang.finance.biz.constant;

public class RedisKeyConstant {
    //图形验证码
    public static final String GRAPHIC_VERIFICATION_CODE="GRAPHIC_VERIFICATION_CODE:";
    //短讯马
    public static final String SMS_CODE="SMS_CODE:";
    public static final String PHONE_CHANGE="PHONE_CHANGE:";
    public static final String CLIENT_TOKEN_KEY = "CLIENT_TOKEN_KEY:";


    //用户图形验证码（登录之后的）
    public static final String USER_GRAPHIC_VERIFICATION_CODE = "USER_GRAPHIC_VERIFICATION_CODE:";
    /**
     * 修改菜单锁
     */
    public static final String CHANGE_MENU_LOCK="CHANGE_MENU_LOCK:";
    /**
     * 修改资源锁
     */
    public static final String CHANGE_RESOURCE_LOCK = "CHANGE_RESOURCE_LOCK";
    /**
     * 科目锁
     */
    public static final String SUBJECT_LOCK = "SUBJECT_LOCK:";

    /**
     * 保存凭证锁
     */
    public static final String SAVE_VOUCHER_LOCK = "SAVE_VOUCHER_LOCK:";


}
