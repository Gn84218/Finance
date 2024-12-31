package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 货币配置（表：currency_config）
 *
 * @date 2023-10-26 00:09:39
 */
@Data
public class GetCurrencyConfigVo {

    @ApiModelProperty(value = "id")
    private Long id;
    
    @ApiModelProperty(value = "编码[RMB，USD]")
    private String code;

    @ApiModelProperty(value = "币别名称[人民币，美元]")
    private String name;

    @ApiModelProperty(value = "汇率")
    private BigDecimal exchangeRate;

    @ApiModelProperty(value = "是否禁用")
    private Boolean disable;

    @ApiModelProperty(value = "是否是本位币，0：否，1：是")
    private Boolean baseCurrencyFlag;
}