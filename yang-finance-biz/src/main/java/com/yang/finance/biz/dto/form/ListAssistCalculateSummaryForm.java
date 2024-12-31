package com.yang.finance.biz.dto.form;

import com.yang.common.dto.PageHelperRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ListAssistCalculateSummaryForm extends PageHelperRequest {
    @ApiModelProperty(value = "编码或名称")
    @Size(max = 50)
    private String codeOrName;

    @ApiModelProperty(value = "辅助核算类别id")
    private Long assistCalculateCateId;
}