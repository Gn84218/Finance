package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListAssistCalculateSupplierVo extends BaseAssistCalculateVo {
    @ApiModelProperty(value = "供应商类别")
    private String supplierCate;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "经营地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String contacts;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "税号")
    private String unifiedSocialCreditCode;
}