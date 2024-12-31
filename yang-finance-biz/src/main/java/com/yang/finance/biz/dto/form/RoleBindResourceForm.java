package com.yang.finance.biz.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleBindResourceForm {
    @ApiModelProperty(value = "角色id")
    @NotNull
    @Range(min = 1, max = Long.MAX_VALUE)
    private Integer roleId;

    @ApiModelProperty(value = "绑定资源id列表")
    private List<Integer> bindResourceIds;

    @ApiModelProperty(value = "解绑资源id列表")
    private List<Integer> unBindResourceIds;
}
