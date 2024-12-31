package com.yang.finance.admin.api.controller;

import com.yang.common.dto.ApiResponse;
import com.yang.finance.biz.dto.form.*;
import com.yang.finance.biz.dto.vo.GetRoleDetailVo;
import com.yang.finance.biz.dto.vo.ListRoleVo;
import com.yang.finance.biz.dto.vo.MenuDataItemVo;
import com.yang.finance.biz.service.SysRoleService;
import com.yang.mybatis.help.PageInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "/sysRole")
@RequiredArgsConstructor
public class SysRoleController {
    final SysRoleService sysRoleService;

    @ApiOperation(value = "新增角色")
    @PostMapping(value = "/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody CreateSysRoleForm form) {
        return ApiResponse.success(sysRoleService.create(form));
    }

    @ApiOperation(value = "查看角色列表")
    @PostMapping(value = "/list")
    public ApiResponse<PageInfo<ListRoleVo>> list(@Valid @RequestBody ListRoleForm form) {
        return ApiResponse.success(sysRoleService.list(form));
    }

    @ApiOperation(value = "删除角色")
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelSysRoleForm form) {
        return ApiResponse.success(sysRoleService.del(form.getId()));
    }

    @ApiOperation(value = "禁用或启用角色")
    @PostMapping(value = "/updateDisable")
    public ApiResponse<Boolean> updateDisable(@Valid @RequestBody UpdateRoleDisableForm form) throws JsonProcessingException {
        return ApiResponse.success(sysRoleService.updateDisable(form));
    }

    @ApiOperation(value = "修改角色")
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody UpdateRoleForm form) {
        return ApiResponse.success(sysRoleService.update(form));
    }

    @ApiOperation(value = "查询角色明细")
    @GetMapping(value = "getById")
    public ApiResponse<GetRoleDetailVo> getById(@Valid @NotNull @RequestParam Integer id) {
        return ApiResponse.success(sysRoleService.getById(id));
    }

    @ApiOperation(value = "查询当前登录用户角色绑定的菜单列表")
    @GetMapping(value = "/listRoleBindMenu")
    public ApiResponse<List<MenuDataItemVo>> listRoleBindMenu() {
        return ApiResponse.success(sysRoleService.listRoleBindMenu());
    }

    @ApiOperation(value = "角色绑定菜单")
    @PostMapping(value = "/roleBindMenu")
    public ApiResponse<Boolean> roleBindResource(@Valid @RequestBody RoleBindMenuForm form) {
        return ApiResponse.success(sysRoleService.roleBindMenu(form));
    }

    @ApiOperation(value = "角色绑定资源")
    @PostMapping(value = "/roleBindResource")
    public ApiResponse<Boolean> roleBindResource(@Valid @RequestBody RoleBindResourceForm form) {
        return ApiResponse.success(sysRoleService.roleBindResource(form));
    }
}
