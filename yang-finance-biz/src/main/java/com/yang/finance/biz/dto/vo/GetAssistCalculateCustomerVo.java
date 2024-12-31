package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetAssistCalculateCustomerVo extends GetAssistCalculateVo {

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

    @ApiModelProperty(value = "省份编码")
    private String provinceCode;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "区县编码")
    private String countyCode;
}