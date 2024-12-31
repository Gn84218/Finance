package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GetAssistCalculateEmployeeVo extends GetAssistCalculateVo {

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "部门编码")
    private String departmentCode;

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "职务")
    private String position;

    @ApiModelProperty(value = "岗位")
    private String job;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "入职日期")
    private Date startDate;

    @ApiModelProperty(value = "离职日期")
    private Date departureDate;
}