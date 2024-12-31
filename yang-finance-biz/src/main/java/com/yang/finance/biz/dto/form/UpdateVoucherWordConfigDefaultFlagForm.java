package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 修改凭证字默认值
 *
 */
@Data
public class UpdateVoucherWordConfigDefaultFlagForm {
    @ApiModelProperty(value = "凭证字Id")
    @NotNull
    @Range(min = 1)
    private Long id;
}