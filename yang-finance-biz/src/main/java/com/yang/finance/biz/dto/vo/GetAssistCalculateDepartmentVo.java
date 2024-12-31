package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GetAssistCalculateDepartmentVo extends GetAssistCalculateVo {

    @ApiModelProperty(value = "负责人")
    private String manager;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "成立日期")
    private Date startDate;

    @ApiModelProperty(value = "撤销日期")
    private Date revokeDate;
}