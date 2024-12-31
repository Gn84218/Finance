package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 删除币别
 *
 * @date 2023-10-26 00:09:39
 */
@Data
public class DelCurrencyConfigForm {
    @NotNull
    @ApiModelProperty(value = "币别id")
    private Long id;
}