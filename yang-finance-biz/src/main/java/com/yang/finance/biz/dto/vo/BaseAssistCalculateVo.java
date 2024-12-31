package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 辅助核算
 *
 */
@Data
public class BaseAssistCalculateVo {
    /**
     * 
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 辅助核算类别名称
     */
    @ApiModelProperty(value = "辅助核算类别名称")
    private String name;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String notes;

    /**
     * 助记码
     */
    @ApiModelProperty(value = "助记码")
    private String mnemonicCode;

    @ApiModelProperty(value = "禁用启用状态，true禁用，false启用")
    private Boolean disable;

    @ApiModelProperty(value = "辅助核算id")
    private Long assistCalculateSummaryId;
}