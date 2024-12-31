package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 辅助核算（表：assist_calculate）
 *
 */
@Data
public class ListAssistCalculateSummaryVo {
    /**
     * 
     */
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "辅助核算id")
    private Long assistCalculateId;

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
     * 助记码
     */
    @ApiModelProperty(value = "助记码")
    private String mnemonicCode;
}