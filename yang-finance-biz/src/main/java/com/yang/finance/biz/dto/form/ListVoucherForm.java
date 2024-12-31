package com.yang.finance.biz.dto.form;

import com.yang.common.dto.PageHelperRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ListVoucherForm extends PageHelperRequest {
    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "用户id")
    private Long memberId;

    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "凭证字配置id")
    private Long voucherWordConfigId;

    @ApiModelProperty(value = "开始凭证号")
    private Integer beginVoucherNumber;

    @ApiModelProperty(value = "结束凭证号")
    private Integer endVoucherNumber;

    @ApiModelProperty(value = "开始凭证日期")
    private Date beginVoucherDate;

    @ApiModelProperty(value = "结束凭证日期")
    private Date endVoucherDate;

    @ApiModelProperty(value = "开始凭证总金额")
    private BigDecimal beginTotalAmount;

    @ApiModelProperty(value = "结束凭证总金额")
    private BigDecimal endTotalAmount;

    @ApiModelProperty(value = "科目id")
    private Long subjectId;

    @ApiModelProperty(value = "排序规则 ，0：凭证号升序，1：凭证号降序，2：凭证日期升序，3：凭证日期降序")
    private Integer sortRule = 0;
}
