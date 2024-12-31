package com.yang.finance.biz.dto.form;

import com.yang.common.dto.PageHelperRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 查询辅助核算（表：subject）
 *
 */
@Data
public class ListAssistCalculateForm extends PageHelperRequest {

    @ApiModelProperty(value = "辅助核算类别id")
    @NotNull
    @Min(value = 1)
    private Long assistCalculateCateId;

    @ApiModelProperty(value = "编码或名称")
    @Size(max = 50)
    private String codeOrName;
}