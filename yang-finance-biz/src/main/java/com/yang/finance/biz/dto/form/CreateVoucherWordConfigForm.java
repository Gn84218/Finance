package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 创建凭证字
 *
 */
@Data
public class CreateVoucherWordConfigForm {

    @ApiModelProperty(value = "凭证字")
    @NotNull
    @Size(min = 1, max = 10)
    private String voucherWord;

    /**
     * 打印标题
     */
    @ApiModelProperty(value = "打印标题")
    @NotBlank
    @Size(min = 1, max = 50)
    private String printTitle;
}