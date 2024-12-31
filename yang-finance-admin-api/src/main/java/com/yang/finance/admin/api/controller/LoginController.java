package com.yang.finance.admin.api.controller;

import com.yang.common.dto.ApiResponse;
import com.yang.common.dto.TokenResponse;
import com.yang.finance.biz.dto.form.*;
//import com.yang.finance.biz.dto.form.GetBase64CodeForm;
import com.yang.finance.biz.service.MemberLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Api(tags = "用户登入模块")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor//构造参数 注解
@Slf4j//日志
public class LoginController {
    final MemberLoginService memberLoginService;

    @ApiOperation("获取客户端ID")
    @GetMapping("/getClientId")
    public ApiResponse<String> getClientId(){
        String result= memberLoginService.getClientId();
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "获取图形验证码")
    @GetMapping(value = "/getBase64Code")
    public ApiResponse<String> getBase64Code(@Validated @ModelAttribute GetBase64CodeForm form  ) {
        String code = memberLoginService.getBase64Code(form);
        return ApiResponse.success(code);
    }

    @ApiOperation("获取简讯验证码")
    @GetMapping("/sendSmsCode")
    //前端信息不用返回 使用Void
    public ApiResponse<Void> sendSmsCode(@Validated @ModelAttribute GetSmsCodeForm form){
        memberLoginService.sendSmsCode(form);
        return ApiResponse.success();
    }

    @ApiOperation(value = "手机密码登录")
    @PostMapping(value = "/phonePasswordLogin")
    public ApiResponse<TokenResponse> phonePasswordLogin(@Validated @RequestBody PhonePasswordLoginForm form) {
        TokenResponse tokenResponse = memberLoginService.phonePasswordLogin(form);
        return ApiResponse.success(tokenResponse);
    }

    @ApiOperation(value = "手机短信登录")
    @PostMapping(value = "/phoneSmsCodeLogin")
    public ApiResponse<TokenResponse> phoneSmsCodeLogin(@Validated @RequestBody PhoneSmsCodeLoginForm request) {
        TokenResponse tokenResponse = memberLoginService.phoneSmsCodeLogin(request);
        return ApiResponse.success(tokenResponse);
    }

    @ApiOperation(value = "获取客户端token")
    @GetMapping(value = "/getClientToken")
    public ApiResponse<TokenResponse> getClientToken(@Validated @ModelAttribute GetClientTokenForm request) {
        TokenResponse result = memberLoginService.getClientToken(request.getClientId());
        return ApiResponse.success(result);
    }
}



