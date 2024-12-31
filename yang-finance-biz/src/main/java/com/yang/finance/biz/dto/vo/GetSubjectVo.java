package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 科目（表：subject）
 *
 */
@Data
public class GetSubjectVo {
    private Long id;

    @ApiModelProperty(value = "父类id")
    private Long pid;

    @ApiModelProperty(value = "父类名称")
    private String parentName;

    @ApiModelProperty(value = "顺序，序号")
    private Integer sort;

    @ApiModelProperty(value = "科目编码")
    private String code;

    @ApiModelProperty(value = "科目名称")
    private String name;

    @ApiModelProperty(value = "助记码")
    private String mnemonicCode;

    @ApiModelProperty(value = "余额方向[1：借，2：贷]")
    private Integer balanceDirection;

    @ApiModelProperty(value = "是否禁用，true禁用，false启用")
    private Boolean disable;

    @ApiModelProperty(value = "1：资产，2：负债，3：权益，4：成本，5：损益")
    private Integer subjectCate;

    @ApiModelProperty(value = "科目类别名称")
    private String subjectCateName;

    @ApiModelProperty(value = "科目核算配置")
    private SubjectCalculateConfigVo subjectCalculateConfigVo;

    @ApiModelProperty(value = "科目类型[0：系统科目，不能删除，1：自定义科目，可自行删除]")
    private Integer subjectType;

    @ApiModelProperty(value = "被使用的次数")
    private Integer useCount;
}