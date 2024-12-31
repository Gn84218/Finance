package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListSubjectByCateAndCodeAndNameVo {
    @ApiModelProperty(value = "科目id")
    private Long id;

    @ApiModelProperty(value = "科目父id")
    private Long pid;

    @ApiModelProperty(value = "科目类别")
    private String subjectCate;

    @ApiModelProperty(value = "科目编码")
    private String code;

    @ApiModelProperty(value = "科目名称")
    private String name;

    @ApiModelProperty(value = "科目全名称（含分类）")
    private String fullName;
}