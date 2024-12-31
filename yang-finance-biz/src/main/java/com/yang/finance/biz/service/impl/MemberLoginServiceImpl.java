package com.yang.finance.biz.service.impl;

import com.yang.common.service.TokenService;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.yang.common.constant.ApiResponseCode;
import com.yang.common.dto.TokenResponse;
import com.yang.common.exception.BizException;
import com.yang.common.exception.ParameterException;
import com.yang.common.util.DateUtil;
import com.yang.common.util.MyUtil;
import com.yang.finance.biz.constant.RedisKeyConstant;
import com.yang.finance.biz.domain.Member;
import com.yang.finance.biz.domain.MemberBindPhone;
import com.yang.finance.biz.dto.AdminDTO;
import com.yang.finance.biz.dto.form.*;
import com.yang.finance.biz.enums.SmsCodeTypeEnum;
import com.yang.finance.biz.service.MemberBindPhoneService;
import com.yang.finance.biz.service.MemberLoginService;
import com.yang.finance.biz.service.MemberService;
import com.yang.finance.biz.service.SmsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberLoginServiceImpl implements MemberLoginService {

    final TokenService<AdminDTO> adminTokenService;
    final RedisTemplate<String, Object> redisTemplate;
    final MemberBindPhoneService memberBindPhoneService;
    final MemberService memberService;
    final PasswordEncoder passwordEncoder;
    final ObjectMapper jsonMapper;
    final SmsService smsService;



    /**
     * 创建一个访问者ID UUID.randomUUID()随机生成
     * @return
     */
    @Override
    public String getClientId() {
        return UUID.randomUUID().toString().replace("-","");//把-清掉
    }

    /**
     * 获取图形验证码
     *
     * 设定大小 把验证码存入redis中
     * @param form
     * @return
     */
    @Override
    public String getBase64Code(GetBase64CodeForm form) {
        //设计验证码图片 大小,字数 以及要多少个字的验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(300, 192, 5, 1000);
        String code = lineCaptcha.getCode();
        log.info("图形验证码:{}",code);
        //TODO: 2024/3/23  将code保存到redis中
        //第一个参数:一个前缀和一个标识符，存储图形验证码相关内容 重点!!写到一个公共类 之后用得到biz.constant.RedisKeyConstant
        //第二个参数:存储的值，即图形验证码的内容
        //第三个参数：表示键的过期时间，在 Redis 中设置存活15 ps第四参数选择时间
        //第四个参数：表示过期时间的单位，这里是分钟
        redisTemplate.opsForValue().set(RedisKeyConstant.GRAPHIC_VERIFICATION_CODE+form.getClientId(),code,15, TimeUnit.MINUTES);
        return lineCaptcha.getImageBase64();
    }

    /**
     * 获取短信验证码
     *
     * 校验图形验证码 60秒内不能重复触发 (做一个缓存用的key)
     * 校验手机号是否注册
     * 随机生成6个数字的手机验证码 加入缓存里
     * @param form
     * @return
     */
    @Override
    public void sendSmsCode(GetSmsCodeForm form) {
        //第一步 校验图形验证码
        checkBase64Code(form.getClientId(), form.getCode());
        //第二步 "SMS_CODE:信息类型+手机号" 存一个 缓存用的key
        String key = RedisKeyConstant.SMS_CODE + form.getSmsCodeType() + form.getPhone();
        //拿短信验证码
        SmsCodeResult smsCodeResult = (SmsCodeResult) redisTemplate.opsForValue().get(key);
        if (smsCodeResult != null) {
            //验证时间 小于60秒报错
            Duration duration = DateUtil.getDuration(smsCodeResult.getGetTime(), DateUtil.getSystemTime());
            if (duration.getSeconds() < 60) {
                throw new BizException("验证码获取太频繁，请稍后重试");
            }
        }
        /**
         * 校验手机号是否 注册,登入
         */
        MemberBindPhone memberBindPhone = memberBindPhoneService.getMemberByPhone(form.getPhone());
        if (form.getSmsCodeType().equals(SmsCodeTypeEnum.REG.getCode()) && memberBindPhone != null) {
            throw new ParameterException("phone", "该手机号已注册！");
        }
        if (form.getSmsCodeType().equals(SmsCodeTypeEnum.LOGIN.getCode()) && memberBindPhone == null) {
            throw new ParameterException("phone", "该手机号未注册！");
        }
        //生成随机6个数字 储存
        int smsCode = MyUtil.getRandom(6);
        smsCodeResult = new SmsCodeResult();
        smsCodeResult.setCode(String.valueOf(smsCode));
        smsCodeResult.setGetTime(DateUtil.getSystemTime());
        //存入缓存中
        redisTemplate.opsForValue().set(key, smsCodeResult, 15, TimeUnit.MINUTES);
        log.info("客户端id{},手机号：{},短信验证码：{}", form.getClientId(), form.getPhone(), smsCode);
        smsService.sendSmsCode(form.getPhone(), smsCodeResult.getCode(), form.getSmsCodeType());
        //TODO 调用第三方短信接口
    }


    /**
     * 校验图形验证码
     *
     * 从缓存中取出验证码
     * 是放掉缓存中的验证码(因为是一次性)
     * 校验缓存中取出的验证码是否与键入的相同 错误抛出异常
     * @param clientId
     * @return
     */
    @Override
    public boolean checkBase64Code(String clientId, String code) {
        //生成图片，获取base64编码字符串 get缓存的key
        String cacheCode = (String) redisTemplate.opsForValue().get(RedisKeyConstant.GRAPHIC_VERIFICATION_CODE + clientId);
        //redisTemplate.delete (删除缓存中的key)  ,验证码是一次性的用完就要删除
        redisTemplate.delete(RedisKeyConstant.GRAPHIC_VERIFICATION_CODE + clientId);
        //如果前端返回的验证码不匹配缓存取出的字串 抛出异常
        if (!code.equalsIgnoreCase(cacheCode)) {
            //抛出一个参数异常方法 ParameterException
            throw new ParameterException("code", "图形验证码错误！");
        }
        return true;
    }
    /**
     * 校验短信验证码
     *
     * 取出缓存中的短讯验证码
     * 清除短信验证码缓存(一次性使用)
     * 验证码为空或不匹配,抛出异常
     * @param phone
     * @param smsCode
     * @param smsCodeType
     * @return
     */
    @Override
    public boolean checkSmsCode(String phone, String smsCode, String smsCodeType) {
        //取出缓存中的短信验证码
        SmsCodeResult cacheSmsCode = (SmsCodeResult) redisTemplate.opsForValue().get(RedisKeyConstant.SMS_CODE + smsCodeType + phone);
        //删掉缓存中的短信验证码 使用一次就消耗
        redisTemplate.delete(RedisKeyConstant.SMS_CODE + smsCodeType + phone);
        //如果缓存中的验证码为空 或者输入的验证码(smsCode)不匹配缓存中取出的,抛出异常
        if (cacheSmsCode == null || !smsCode.equals(cacheSmsCode.getCode())) {
            throw new ParameterException("smsCode", "短信证码错误，请重新获取验证码！");
        }
        return true;
    }

    /**
     * 手机账号密码登录
     *
     * 校验验证码
     * 取出 查询手机号是否被禁用
     * 用户信息为空或密码为空 抛出异常
     * matches方法比较 密码是否匹配
     * @param form
     * @return
     */
    @Override
    public TokenResponse phonePasswordLogin(PhonePasswordLoginForm form) {
        //传入游客ID以及输入的验证码做校验
        checkBase64Code(form.getClientId(), form.getCode());
        //根据手机号 获取用户讯息 并 查看手机号是否被禁用
        MemberBindPhone memberBindPhone = memberBindPhoneService.getMemberByPhone(form.getPhone());
        //输入的用户讯息等于空 或者 密码等于空 抛出异常
        if (memberBindPhone == null || Strings.isBlank(memberBindPhone.getPassword())) {
            throw new BizException(ApiResponseCode.ACCOUNT_PASSWORD_ERROR.getCode(),
                    ApiResponseCode.ACCOUNT_PASSWORD_ERROR.getMessage());
        }
        //知试重点 使用matches方法比较(前端明文输入密码 比较 后端加密密码) !明文比较加密
        if (!passwordEncoder.matches(form.getPassword(), memberBindPhone.getPassword())) {
            throw new BizException(ApiResponseCode.ACCOUNT_PASSWORD_ERROR.getCode(),
                    ApiResponseCode.ACCOUNT_PASSWORD_ERROR.getMessage());
        }
        //游客生成的ID 租户ID 多个角色的ID 返回
        Member member = memberService.get(memberBindPhone.getMemberId());
        return loginSuccess(memberBindPhone.getMemberId(), member.getTenantId(), member.getSysRoleIds());
    }

    /**
     * 手机短信登录
     *
     * @param form
     * @return
     */
    @Override
    public TokenResponse phoneSmsCodeLogin(PhoneSmsCodeLoginForm form) {
        //以手机号为准校验短信内容
        checkSmsCode(form.getPhone(), form.getSmsCode(), SmsCodeTypeEnum.LOGIN.getCode());
        //根据手机获取用户信息
        MemberBindPhone memberBindPhone = memberBindPhoneService.getMemberByPhone(form.getPhone());
        //手机号未注册
        if (memberBindPhone == null) {
            throw new ParameterException("phone",
                    "该手机号未注册");
        }
        Member member = memberService.get(memberBindPhone.getMemberId());
        return loginSuccess(memberBindPhone.getMemberId(), member.getTenantId(), member.getSysRoleIds());
    }

    /**
     * 获取客户端token
     *
     * @param clientId
     * @return
     */
    @Override
    public TokenResponse getClientToken(String clientId) {
        return (TokenResponse) redisTemplate.opsForValue().get(RedisKeyConstant.CLIENT_TOKEN_KEY + clientId);
    }

    /**
     * 把 游客ID,租户ID,角色ID 存入token 存入redis
     * 返回 刚存入的token
     * @param memberId
     * @param tenantId
     * @param sysRoleIds
     * @return
     */
    private TokenResponse loginSuccess(Long memberId, Long tenantId, String sysRoleIds) {
        try {
            AdminDTO adminDTO = new AdminDTO();
            //创建用户ID
            adminDTO.setId(memberId);
            //创建租户ID
            adminDTO.setTenantId(tenantId);
            //存入角色ID 通过jsonMapper 转换实体对象
            adminDTO.setSysRoleIds(jsonMapper.readValue(sysRoleIds, new TypeReference<Set<Long>>() {
            }));
            //存入token ,和缓存
            adminTokenService.setToken(adminDTO);
            redisTemplate.opsForValue().set(RedisKeyConstant.CLIENT_TOKEN_KEY + memberId, adminDTO.getToken(), 10, TimeUnit.MINUTES);
            return adminDTO.getToken();
        } catch (Exception ex) {
            throw new BizException("登录失败", ex);
        }
    }



}
