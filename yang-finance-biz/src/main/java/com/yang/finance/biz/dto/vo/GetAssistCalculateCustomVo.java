package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetAssistCalculateCustomVo extends GetAssistCalculateVo {
    @ApiModelProperty(value = "自定义字段1")
    private String c1;

    @ApiModelProperty(value = "自定义字段2")
    private String c2;

    @ApiModelProperty(value = "自定义字段4")
    private String c3;

    @ApiModelProperty(value = "自定义字段4")
    private String c4;

    @ApiModelProperty(value = "自定义字段5")
    private String c5;

    @ApiModelProperty(value = "自定义字段6")
    private String c6;

    @ApiModelProperty(value = "自定义字段7")
    private String c7;

    @ApiModelProperty(value = "自定义字段8")
    private String c8;
}