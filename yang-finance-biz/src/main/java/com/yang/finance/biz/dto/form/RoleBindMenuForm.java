package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleBindMenuForm {
    @ApiModelProperty(value = "角色id")
    @NotNull
    @Range(min = 1, max = Long.MAX_VALUE)
    private Integer roleId;
    //打勾
    @ApiModelProperty(value = "绑定菜单id列表")
    private List<Integer> bindMenuIds;
    //未勾
    @ApiModelProperty(value = "解绑菜单id列表")
    private List<Integer> unBindMenuIds;
}