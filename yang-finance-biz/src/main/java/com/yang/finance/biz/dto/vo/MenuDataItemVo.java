package com.yang.finance.biz.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MenuDataItemVo {
    @ApiModelProperty(value = "菜单id")
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单路由")
    private String path;

    @ApiModelProperty(value = "是否使用布局")
    private Boolean layout;

    @ApiModelProperty(value = "菜单组件")
    private String component;

    @ApiModelProperty(value = "菜单重定向地址")
    private String redirect;

    @ApiModelProperty(value = "是否隐藏菜单")
    private Boolean hideInMenu;

    @ApiModelProperty(value = "子菜单")
    private List<MenuDataItemVo> routes;
}