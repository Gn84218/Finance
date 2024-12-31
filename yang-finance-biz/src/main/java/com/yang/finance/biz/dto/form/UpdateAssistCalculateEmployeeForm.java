package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 辅助核算（职员）
 *
 */
@Data
public class UpdateAssistCalculateEmployeeForm extends UpdateAssistCalculateForm {
    @ApiModelProperty(value = "性别")
    @Range(min = 0, max = 2)
    private Integer sex;

    @ApiModelProperty(value = "部门编码")
    private String departmentCode;

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "职务")
    @Size(max = 50)
    private String position;

    @ApiModelProperty(value = "岗位")
    @Size(max = 50)
    private String job;

    @ApiModelProperty(value = "手机")
    @Pattern(regexp = "^(\\d{11})?$", message = "手机格式不正确")
    private String phone;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "入职日期")
    private Date startDate;

    @ApiModelProperty(value = "离职日期")
    private Date departureDate;
}