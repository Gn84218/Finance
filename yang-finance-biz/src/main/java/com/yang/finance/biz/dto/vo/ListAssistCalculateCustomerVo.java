package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 辅助核算客户列表
 *
 */
@Data
public class ListAssistCalculateCustomerVo extends BaseAssistCalculateVo{
    @ApiModelProperty(value = "客户类别")
    private String customerCate;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String contacts;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "税号")
    private String unifiedSocialCreditCode;
}