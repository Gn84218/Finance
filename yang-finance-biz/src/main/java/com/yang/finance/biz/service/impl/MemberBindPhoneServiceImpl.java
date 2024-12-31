package com.yang.finance.biz.service.impl;

import com.yang.common.exception.BizException;
import com.yang.common.exception.ParameterException;
import com.yang.common.service.TokenService;
import com.yang.finance.biz.domain.MemberBindPhone;
import com.yang.finance.biz.dto.AdminDTO;
import com.yang.finance.biz.dto.form.UpdatePasswordForm;
import com.yang.finance.biz.dto.form.UpdatePhoneForm;
import com.yang.finance.biz.mapper.MemberBindPhoneMapper;
import com.yang.finance.biz.service.MemberBindPhoneService;
import com.yang.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.yang.finance.biz.domain.MemberBindPhoneField.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberBindPhoneServiceImpl implements MemberBindPhoneService {

    final MemberBindPhoneMapper memberBindPhoneMapper;
    final PasswordEncoder passwordEncoder;
    final TokenService<AdminDTO> tokenService;


    /**
     * 根据手机号获取用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public MemberBindPhone getMemberByPhone(String phone) {

        //使用MyBatis插件orm 构建一个MemberBindPhone的表
        MyBatisWrapper<MemberBindPhone> myBatisWrapper = new MyBatisWrapper<>();
        myBatisWrapper.select(MemberId, Phone, Password)
                .whereBuilder().andEq(setPhone(phone))//构造条件 存入手机号
                .andEq(setDisable(false));//是否禁用
        //andEq(表里手机号等于前端传入手机号)
        // select member_id,phone,password from member_bind_phone where phone = ?
        return memberBindPhoneMapper.topOne(myBatisWrapper);//执行这条sql语句
    }

    /**
     * 手机号注册
     *
     * @param phone
     * @param memberId
     * @param password
     * @return
     */
    @Override
    public boolean reg(String phone, long memberId, String password) {
        MemberBindPhone memberBindPhone = new MemberBindPhone();
        memberBindPhone.setMemberId(memberId);
        memberBindPhone.setPhone(phone);
        memberBindPhone.setPassword(passwordEncoder.encode(password));//密码存入 使用加密
        memberBindPhone.initDefault();
        return memberBindPhoneMapper.insert(memberBindPhone) > 0;
    }

    /**
     * 修改密码
     *
     * @param form
     * @return
     */
    @Override
    public boolean updatePassword(UpdatePasswordForm form) {
        if (!Objects.equals(form.getPassword(), form.getConfirmPassword())) {
            throw new ParameterException("两次输入的密码不一致");
        }
        MemberBindPhone memberBindPhone = getById(tokenService.getThreadLocalUserId());
        if (memberBindPhone == null) {
            throw new BizException("账号信息不存在");
        }
        if (!passwordEncoder.matches(form.getOldPassword(), memberBindPhone.getPassword())) {
            throw new BizException("旧密码不正确");
        }
        //加密新密码 存入数据库
        String newPassword = passwordEncoder.encode(form.getPassword());
        MyBatisWrapper<MemberBindPhone> wrapper = new MyBatisWrapper<>();
        wrapper.update(setPassword(newPassword))
                .whereBuilder()
                .andEq(setMemberId(tokenService.getThreadLocalUserId()))
                .andEq(setDisable(false));
        if (memberBindPhoneMapper.updateField(wrapper) == 0) {
            throw new BizException("密码修改失败");
        }
        tokenService.clearToken();
        return true;
    }

    /**
     * 获取手机账号信息
     *
     * @param memberId
     * @return
     */
    @Override
    public MemberBindPhone getById(long memberId) {
        MyBatisWrapper<MemberBindPhone> wrapper = new MyBatisWrapper<>();
        wrapper.select(Password)
                .whereBuilder()
                .andEq(setMemberId(tokenService.getThreadLocalUserId()));
        return memberBindPhoneMapper.get(wrapper);
    }

    /**
     * 修改手机号
     *
     * @param form
     * @return
     */
    @Override
    public boolean updatePhone(UpdatePhoneForm form) {
        MyBatisWrapper<MemberBindPhone> wrapper = new MyBatisWrapper<>();
        wrapper.update(setPhone(form.getPhone()))
                .whereBuilder()
                .andEq(setMemberId(tokenService.getThreadLocalUserId()))
                .andEq(setDisable(false));
        return memberBindPhoneMapper.updateField(wrapper) > 0;
    }

}
