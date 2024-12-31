package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 凭证详情
 *
 */
@Data
public class GetVoucherWordConfigVo {
    /**
     *
     */
    private Integer id;
    /**
     * 凭证纸字
     */
    @ApiModelProperty(value = "凭证字")
    private String voucherWord;

    /**
     * 打印标题
     */
    @ApiModelProperty(value = "打印标题")
    private String printTitle;

    /**
     * 是否是默认[0:否，1：默认]
     */
    @ApiModelProperty(value = "是否是默认")
    private Boolean defaultFlag;
}