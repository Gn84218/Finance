package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetAssistCalculateVo {
    @ApiModelProperty(value = "id")
    private Long     id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "助记码")
    private String mnemonicCode;
}