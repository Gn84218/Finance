package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 账套（表：account_book）
 */
@Data
public class AccountBookDisableForm {
    /**
     * 公司名称
     */
    @ApiModelProperty(value = "账套id")
    @NotNull
    @Min(value = 1)
    private Long id;

    @ApiModelProperty(value = "true禁用，false：启用")
    @NotNull
    private Boolean disable;
}