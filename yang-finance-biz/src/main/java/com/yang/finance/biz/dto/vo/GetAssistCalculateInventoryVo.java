package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GetAssistCalculateInventoryVo extends GetAssistCalculateVo {
    @ApiModelProperty(value = "规格型号")
    private String specifications;

    @ApiModelProperty(value = "存货类别")
    private String inventoryCate;

    @ApiModelProperty(value = "计量单位")
    private String units;

    @ApiModelProperty(value = "启用日期")
    private Date startDate;

    @ApiModelProperty(value = "停用日期")
    private Date endDate;
}