package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetAssistCalculateCashFlowVo {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "现金流编码")
    private String code;

    @ApiModelProperty(value = "现金流名称")
    private String name;

    @ApiModelProperty(value = "现金流类别")
    private String cashFlowCate;

    @ApiModelProperty(value = "备注")
    private String notes;
}