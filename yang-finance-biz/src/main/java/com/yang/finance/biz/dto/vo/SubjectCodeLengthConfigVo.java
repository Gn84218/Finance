package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 科目编码长度配置
 *
 */
@Data
public class SubjectCodeLengthConfigVo {
    @ApiModelProperty(value = "第一级编码长度")
    private Integer depthCodeLengthOne;

    @ApiModelProperty(value = "第二级编码长度")
    private Integer depthCodeLengthTwo;

    @ApiModelProperty(value = "第三级编码长度")
    private Integer depthCodeLengthThree;

    @ApiModelProperty(value = "第四级编码长度")
    private Integer depthCodeLengthFour;
}