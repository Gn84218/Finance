package com.yang.finance.biz.service.impl;

import com.yang.common.exception.BizException;
import com.yang.common.service.TokenService;
import com.yang.finance.biz.domain.SysRoleBindMenu;
import com.yang.finance.biz.dto.AdminDTO;
import com.yang.finance.biz.dto.form.RoleBindMenuForm;
import com.yang.finance.biz.mapper.SysRoleBindMenuMapper;
import com.yang.finance.biz.service.SysRoleBindMenuService;
import com.yang.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.yang.finance.biz.domain.SysRoleBindMenuField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysRoleBindMenuServiceImpl implements SysRoleBindMenuService {
    final SysRoleBindMenuMapper mapper;
    final TokenService<AdminDTO> tokenService;
    final TransactionTemplate transactionTemplate;

    /**
     * 角色绑定资源列表
     *
     * @param form
     * @return
     */
    @Override
    public boolean roleBindMenu(RoleBindMenuForm form) {
        if (CollectionUtils.isEmpty(form.getBindMenuIds()) && CollectionUtils.isEmpty(form.getUnBindMenuIds())) {
            throw new BizException("您未做任何更改，不能提交绑定");
        }
        List<SysRoleBindMenu> sysRoleBindMenuList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(form.getBindMenuIds())) {
            //检测角色是否已经绑定过资源
            checkRoleBindMenuIds(form.getRoleId(), form.getBindMenuIds());
            //厉遍所有勾选的菜单 做储存 (后者每被执行一次,存入一次:厉遍集合)
            for (Integer menuId : form.getBindMenuIds()) {
                SysRoleBindMenu sysRoleBindMenu = new SysRoleBindMenu();
                sysRoleBindMenu.setSysRoleId(form.getRoleId());
                sysRoleBindMenu.setSysMenuId(menuId);//存入当前菜单ID
                sysRoleBindMenu.setMemberId(tokenService.getThreadLocalUserId());
                sysRoleBindMenu.setUpdateMemberId(tokenService.getThreadLocalUserId());
                sysRoleBindMenu.initDefault();//初始化数据
                sysRoleBindMenuList.add(sysRoleBindMenu);
            }
        }
        // transactionTemplate.execute((transactionStatus) -> {} 知试点:整段代码数据只要有一条错误,终止所有动作,不传递
        transactionTemplate.execute((transactionStatus) -> {
            //批量插入绑定菜单集合
            if (!CollectionUtils.isEmpty(form.getBindMenuIds())) {
                return mapper.insertBatch(sysRoleBindMenuList) > 0;
            }

            //解绑 等于 删除(更改资料为已删除)
            if (!CollectionUtils.isEmpty(form.getUnBindMenuIds())) {
                MyBatisWrapper<SysRoleBindMenu> wrapper = new MyBatisWrapper<>();
                wrapper.update(DelFlag, true)
                        .whereBuilder()
                        .andEq(SysRoleId, form.getRoleId())
                        .andIn(SysMenuId, form.getUnBindMenuIds())//根据前者字段 查询后者集合符合字段的值
                        .andEq(DelFlag, false);
                //使用插件更新交给数据库
                //.updateField(存入更新数量)
                int updateRows = mapper.updateField(wrapper);
                //更新解绑数量 与 解绑不匹配
                if (updateRows != form.getUnBindMenuIds().size()) {
                    throw new BizException("解绑失败");
                }
            }
            return true;
        });
        return true;
    }

    /**
     * 查询绑定的资源列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> listBindMenuIdByRoleId(int roleId) {
        MyBatisWrapper<SysRoleBindMenu> wrapper = new MyBatisWrapper<>();
        wrapper.select(SysMenuId)
                .whereBuilder()
                .andEq(SysRoleId, roleId)
                .andEq(DelFlag, false);
        List<SysRoleBindMenu> sysRoleBindMenuList = mapper.list(wrapper);
        //取出所有绑定菜单中的ID
        return sysRoleBindMenuList.stream().map(SysRoleBindMenu::getSysMenuId)
                .collect(Collectors.toList());
    }
    /*
     *确认是否重复绑定了 (数据库资料)
     *
     * 角色ID,绑定菜单ID
     */
    private void checkRoleBindMenuIds(int roleId, List<Integer> menuIds) {
        MyBatisWrapper<SysRoleBindMenu> wrapper = new MyBatisWrapper<>();
        wrapper.whereBuilder()
                .andEq(SysRoleId, roleId)
                .andIn(SysMenuId, menuIds) //符合SysMenuId字段的 menuIds所有值
                .andEq(DelFlag, false);
        //统计(count)
        if (mapper.count(wrapper) > 0) {
            throw new BizException("角色不要重复绑定菜单");
        }
    }
}
