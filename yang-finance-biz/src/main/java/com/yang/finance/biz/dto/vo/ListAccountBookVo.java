package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ListAccountBookVo {
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "增值税种类")
    private String valueAddedTaxCate;

    @ApiModelProperty(value = "会计准则")
    private String accountingStandard;

    @ApiModelProperty(value = "启用时间")
    private Date startTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "是否启用凭证审核")
    private Boolean enableVoucherVerify;

    @ApiModelProperty(value = "是否启用")
    private Boolean disable;
}
