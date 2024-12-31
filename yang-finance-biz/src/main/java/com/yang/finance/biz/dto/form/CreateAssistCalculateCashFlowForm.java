package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 辅助核算（现金流）
 *
 */
@Data
public class CreateAssistCalculateCashFlowForm extends CreateAssistCalculateForm {
    @ApiModelProperty(value = "现金流类别")
    private String cashFlowCate;
}