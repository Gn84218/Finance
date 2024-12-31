package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateSysRoleForm {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @NotBlank
    private String roleName;

    /**
     * 是否禁用
     */
    @ApiModelProperty(value = "是否禁用")
    @NotNull
    private Boolean disable;
}