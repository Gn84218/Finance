package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 辅助核算（项目）
 *
 */
@Data
public class CreateAssistCalculateProjectForm extends CreateAssistCalculateForm {

    @ApiModelProperty(value = "负责部门")
    private String responsibleDepartment;

    @ApiModelProperty(value = "责任人")
    private String responsiblePerson;

    @ApiModelProperty(value = "手机")
    @Pattern(regexp = "^(\\d{11})?$", message = "手机格式不正确")
    private String phone;

    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    private Date endDate;
}