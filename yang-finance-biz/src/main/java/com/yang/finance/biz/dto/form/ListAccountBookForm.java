package com.yang.finance.biz.dto.form;

import com.yang.common.dto.PageHelperRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListAccountBookForm extends PageHelperRequest {
    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    /**
     * 是否禁用；true：禁用；false：启用
     */
    @ApiModelProperty(value = "是否禁用；true：禁用；false：启用")
    private Boolean disable;
}
