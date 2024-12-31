package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateEmailAndNameForm {
    @ApiModelProperty(value = "邮件地址")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",message = "邮箱格式不正确")
    @NotBlank
    private String email;

    @ApiModelProperty(value = "姓名")
    @NotBlank
    @Size(max = 50)
    private String name;

}
