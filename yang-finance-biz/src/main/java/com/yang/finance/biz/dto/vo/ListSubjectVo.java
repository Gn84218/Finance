package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 查询科目列表
 *
 */
@Data
public class ListSubjectVo {
    private Long id;

    @ApiModelProperty(value = "父类id")
    private Integer pid;

    @ApiModelProperty(value = "顺序，序号")
    private Integer sort;

    @ApiModelProperty(value = "科目编码")
    private String code;

    @ApiModelProperty(value = "科目名称")
    private String name;

    @ApiModelProperty(value = "助记码")
    private String mnemonicCode;

    @ApiModelProperty(value = "余额方向")
    private String balanceDirectionText;

    @ApiModelProperty(value = "是否禁用，true禁用，false启用")
    private Boolean disable;

    @ApiModelProperty(value = "1：资产，2：负债，3：权益，4：成本，5：损益")
    private String subjectCateText;

    @ApiModelProperty(value = "计量单位")
    private String unitOfMeasurement;

    @ApiModelProperty(value = "辅助核算列表")
    private String assistCalculateText;

    @ApiModelProperty(value = "外币核算列表")
    private String currencyConfigText;

    @ApiModelProperty(value = "是否启用期末调汇")
    private Boolean endOfYearCurrencyRevaluationFlag;

    @ApiModelProperty(value = "科目层级")
    private Integer level;

    @ApiModelProperty(value = "科目类型[0：系统科目，不能删除，1：自定义科目，可自行删除]")
    private Byte subjectType;

    @ApiModelProperty(value = "节点深度")
    private Integer nodeDepth;

    @ApiModelProperty(value = "使用计数")
    private Integer useCount;

    @ApiModelProperty(value = "上级科目是否禁用")
    private Boolean parentSubjectDisable;
}