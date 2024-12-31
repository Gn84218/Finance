package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class GenerateMpRegCodeForm {

    /**
     * 客户端id
     *
     * 用于微信校验 验证码用的 (战时没有微信功能,无视即可  )
     */
    @ApiModelProperty(value = "客户端id")
    @NotBlank(message = "客户端id不能为空")
    @Pattern(regexp = "^[0-9A-Za-z]{6,32}$", message = "clientId非法")
    private String clientId;
}
