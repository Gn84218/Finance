package com.yang.finance.admin.api.controller;

import com.yang.common.dto.ApiResponse;
import com.yang.finance.biz.service.SysRoleBindMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "系统角色绑定菜单")
@RestController
@RequestMapping(value = "/sysRoleBindMenu")
@RequiredArgsConstructor
@Slf4j
@Validated
public class SysRoleBindMenuController {
    final SysRoleBindMenuService sysRoleBindMenuService;

    @ApiOperation(value = "查询绑定的菜单列表")
    @GetMapping(value = "/listBindMenuIdByRoleId")
    public ApiResponse<List<Integer>> listBindMenuIdByRoleId(@RequestParam int roleId) {
        return ApiResponse.success(sysRoleBindMenuService.listBindMenuIdByRoleId(roleId));
    }
}
