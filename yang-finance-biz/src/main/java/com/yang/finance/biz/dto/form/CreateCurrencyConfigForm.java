package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 创建币别
 *
 * @date 2023-10-26 00:09:39
 */
@Data
public class CreateCurrencyConfigForm {

    @ApiModelProperty(value = "编码[RMB，USD]")
    @NotBlank
    private String code;

    @ApiModelProperty(value = "币别名称[人民币，美元]")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "汇率")
    @NotNull
    private BigDecimal exchangeRate;
}