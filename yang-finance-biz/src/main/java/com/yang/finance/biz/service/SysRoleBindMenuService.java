package com.yang.finance.biz.service;

import com.yang.finance.biz.dto.form.RoleBindMenuForm;

import java.util.List;

public interface SysRoleBindMenuService {
    /**
     * 角色绑定菜单列表
     * @param form
     * @return
     */
    boolean roleBindMenu(RoleBindMenuForm form);

    /**
     * 查询绑定的资源列表
     * @param roleId
     * @return
     */
    List<Integer> listBindMenuIdByRoleId(int roleId);
}
