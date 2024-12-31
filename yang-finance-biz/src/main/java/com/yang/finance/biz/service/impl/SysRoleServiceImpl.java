package com.yang.finance.biz.service.impl;

import com.yang.common.constant.CommonConstant;
import com.yang.common.dto.PageHelperRequest;
import com.yang.common.exception.BizException;
import com.yang.common.service.TokenService;
import com.yang.common.util.DateUtil;
import com.yang.finance.biz.config.ObjectConvertor;
import com.yang.finance.biz.domain.SysMenu;
import com.yang.finance.biz.domain.SysResource;
import com.yang.finance.biz.domain.SysRole;
import com.yang.finance.biz.dto.AdminDTO;
import com.yang.finance.biz.dto.form.*;
import com.yang.finance.biz.dto.vo.GetRoleDetailVo;
import com.yang.finance.biz.dto.vo.ListRoleVo;
import com.yang.finance.biz.dto.vo.MenuDataItemVo;
import com.yang.finance.biz.mapper.SysRoleMapper;
import com.yang.finance.biz.service.*;
import com.yang.mybatis.help.Criteria;
import com.yang.mybatis.help.MyBatisWrapper;
import com.yang.mybatis.help.PageInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.yang.finance.biz.domain.SysRoleField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {
    final SysRoleMapper sysRoleMapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;
    final ObjectMapper jsonMapper;
    final RedisTemplate<String, Object> redisTemplate;
    final SysMenuService sysMenuService;
    final MemberService memberService;
    final SysResourceService sysResourceService;
    final SysRoleBindMenuService sysRoleBindMenuService;
    final SysRoleBindResourceService sysRoleBindResourceService;

    /**
     * 保存角色路由
     *
     * @param form
     * @return
     */
    @Override
    public boolean create(CreateSysRoleForm form) {
        SysRole sysRole = new SysRole();
        sysRole.initDefault();
        sysRole.setMemberId(tokenService.getThreadLocalUserId());
        sysRole.setUpdateMemberId(sysRole.getMemberId());
        sysRole.setRoleName(form.getRoleName());
        sysRole.setDisable(form.getDisable());

        boolean result = sysRoleMapper.insert(sysRole) > 0;
        if (!result) {
            throw new BizException("保存角色路由失败");
        }
        return true;

    }

    /**
     * 查看角色列表(键入查询)
     *
     * @param form
     * @return
     */
    @Override
    public PageInfo<ListRoleVo> list(ListRoleForm form) {
        //建构一个orm 查询分页
        MyBatisWrapper<SysRole> myBatisWrapper = new MyBatisWrapper<>();
        myBatisWrapper.select(Id, RoleName, Disable)
                .page(form.getPageNum(), form.getPageSize());
        Criteria where = myBatisWrapper.whereBuilder()
                .andEq(setDelFlag(false));
        //模糊查询 键入角色名
        if (Strings.isNotBlank(form.getRoleName())) {
            where.andLike(RoleName, "%" + form.getRoleName() + "%");
        }
        //存入 键入的选择是否为空
        if (form.getDisable() != null) {
            where.andEq(setDisable(form.getDisable()));
        }
        //根据创建时间排序
        myBatisWrapper.orderByDesc(CreateTime);
        //构建分页查询数据库
        PageInfo<SysRole> sysRolePageInfo = myBatisWrapper.listPage(sysRoleMapper);
        //转成返回参数给前端
        return objectConvertor.toListRoleVoPage(sysRolePageInfo);
    }

    /**
     * 查看角色列表(现有资源)
     *
     * @param form
     * @return
     */
    @Override
    public List<SysRole> list(PageHelperRequest form) {
        MyBatisWrapper<SysRole> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, RoleName, Disable, DelFlag)
                .page(form.getPageNum(), form.getPageSize())
                .whereBuilder();

        wrapper.orderByDesc(CreateTime);
        return sysRoleMapper.list(wrapper);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @Override
    public boolean del(int id) {
        MyBatisWrapper<SysRole> wrapper = new MyBatisWrapper<>();
        wrapper.update(setDelFlag(true))
                .update(setUpdateTime(DateUtil.getSystemTime()))
                .update(setUpdateMemberId(tokenService.getThreadLocalUserId()))
                .whereBuilder()
                .andEq(setId(id))
                .andEq(setDelFlag(false));
        //资料如果为已删除 返回false
        if (sysRoleMapper.updateField(wrapper) == 0) {
            return false;
        }
        //todo 缓存
        //删除角色绑定的菜单缓存
        deleteSysRoleMenuCache(id);
        //删除角色绑定的资源缓存
        deleteSysRoleResourceCache(id);
//        //删除会员角色id
//        //todo 后续通过mq发送消息调用删除
//        //memberService.delRoleIds(id);
        return true;
    }

    /**
     * 禁用角色
     *
     * @param form
     * @return
     */
    @Override
    public boolean updateDisable(UpdateRoleDisableForm form) throws JsonProcessingException {
        MyBatisWrapper<SysRole> wrapper = new MyBatisWrapper<>();
        wrapper.update(setDisable(form.getDisable()))
                .update(setUpdateTime(DateUtil.getSystemTime()))
                .update(setUpdateMemberId(tokenService.getThreadLocalUserId()))
                .whereBuilder()
                .andEq(setId(form.getId()))
                .andEq(setDelFlag(false))
                .andEq(setDisable(!form.getDisable()));
        if (sysRoleMapper.updateField(wrapper) == 0) {
            throw new BizException("禁用或启用失败");
        }
// todo
//        if (form.getDisable()) {
//            deleteSysRoleMenuCache(form.getId());
//            deleteSysRoleResourceCache(form.getId());
//        } else {
//            setSysRoleMenuCache(form.getId());
//            setSysRoleResourceCache(form.getId());
//        }
        return true;
    }

    /**
     * 修改角色
     *
     * @param form
     * @return
     */
    @Override
    public boolean update(UpdateRoleForm form) {
        MyBatisWrapper<SysRole> wrapper = new MyBatisWrapper<>();
        wrapper.update(setRoleName(form.getRoleName()))
                .whereBuilder()
                .andEq(setId(form.getId()))
                .andEq(setDelFlag(false));

        return sysRoleMapper.updateField(wrapper) > 0;
    }

    /**
     * 获取角色明细
     *
     * @param id
     * @return
     */
    @Override
    public GetRoleDetailVo getById(int id) {
        MyBatisWrapper<SysRole> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, RoleName)
                .whereBuilder()
                .andEq(setId(id))
                .andEq(setDelFlag(false));
        SysRole sysRole = sysRoleMapper.get(wrapper);
        return objectConvertor.toGetRoleDetailVo(sysRole);
    }

    /**
     * 角色绑定资源列表
     *
     * @param form
     * @return
     */
    @Override
    public boolean roleBindMenu(RoleBindMenuForm form) {
        //先排错
        //如果绑定菜单列表有数据
        if (!CollectionUtils.isEmpty(form.getBindMenuIds())) {
            //根据菜单ID查询列表
            List<SysMenu> sysMenus = sysMenuService.listByIds(form.getBindMenuIds());
            //可勾选的菜单列表数量,对不上数据库数量 报错
            if (form.getBindMenuIds().size() != sysMenus.size()) {
                throw new BizException("资源非法");
            }
        }
        //绑定业务 把成功绑定的菜单放入缓存
        if (sysRoleBindMenuService.roleBindMenu(form)) {
            setSysRoleMenuCache(form.getRoleId());
        }
        return true;
    }

    /**
     * 将所有角色绑定的资源设置到缓存中(通过定时任务触发)
     */
    @Override
    public void setSysRoleMenuCache() {
        PageHelperRequest form = new PageHelperRequest();
        form.setPageNum(1);
        form.setPageSize(100);
        List<SysRole> sysRoles = null;
        List<SysMenu> sysMenus = sysMenuService.list();
        //现有所有菜单ID,角色ID不为空
        while (!CollectionUtils.isEmpty(sysRoles = list(form))) {
            for (SysRole sysRole : sysRoles) {
                if (sysRole.getDisable() || sysRole.getDelFlag()) {
                    deleteSysRoleMenuCache(sysRole.getId());
                    continue;
                }
                //查询绑定的资源id列表
                List<Integer> menuIds = sysRoleBindMenuService.listBindMenuIdByRoleId(sysRole.getId()); //根据角色ID查询绑定菜单IDs
                List<MenuDataItemVo> menuDataItemVos = listRoleBindMenu(sysRole.getId(), menuIds, sysMenus);
                updateSysRoleMenuCache(sysRole.getId(), menuDataItemVos);
            }
            form.setPageNum(form.getPageNum() + 1);
        }
    }

    /**
     * 查询当前登录用户角色绑定的菜单列表
     *
     * @return
     */
    @Override
    public List<MenuDataItemVo> listRoleBindMenu() {
        //从token中取出该使用者永有的角色ID
        Set<Long> roleIds = tokenService.getThreadLocalUser().getSysRoleIds();
        //从缓存中获取
        return listRoleMenuIdByCache(roleIds);
    }

    /**
     * 角色绑定资源列表
     *
     * @param form
     * @return
     */
    @Override
    public boolean roleBindResource(RoleBindResourceForm form) {
        if (!CollectionUtils.isEmpty(form.getBindResourceIds())) {
            List<SysResource> sysResources = sysResourceService.listByIds(form.getBindResourceIds());
            if (form.getBindResourceIds().size() != sysResources.size()) {
                throw new BizException("资源非法");
            }
        }
        if (sysRoleBindResourceService.roleBindResource(form)) {
            setSysRoleResourceCache(form.getRoleId());
        }
        return true;
    }

    /**
     * 将所有角色绑定的资源设置到缓存中(通过定时任务触发)
     */
    @Override
    public void setSysRoleResourceCache() {
        PageHelperRequest form = new PageHelperRequest();
        form.setPageNum(1);
        List<SysRole> sysRoles = null;
        while (!CollectionUtils.isEmpty(sysRoles = list(form))) {
            for (SysRole sysRole : sysRoles) {
                if (sysRole.getDisable() || sysRole.getDelFlag()) {
                    deleteSysRoleResourceCache(sysRole.getId());
                    continue;
                }
                //查询绑定的资源id列表
                List<Integer> resourceIds = sysRoleBindResourceService.listBindResourceIdByRoleId(sysRole.getId());
                List<SysResource> sysResources = sysResourceService.listByIds(resourceIds);
                updateSysRoleResourceCache(sysRole.getId(),
                        sysResources.stream().map(SysResource::getPath).collect(Collectors.toSet()));
            }
            form.setPageNum(form.getPageNum() + 1);
        }
    }

    /**
     * 将某个角色绑定的菜单设置到缓存中
     * !!!redis知试点
     * @param roleId
     */
    private void setSysRoleMenuCache(int roleId) {
        //查询菜单
        List<SysMenu> sysMenus = sysMenuService.list();
        //查询绑定的资源id列表
        List<Integer> menuIds = sysRoleBindMenuService.listBindMenuIdByRoleId(roleId);//取出所有已绑定的菜单ID
        List<MenuDataItemVo> menuDataItemVos = listRoleBindMenu(roleId, menuIds, sysMenus);
        updateSysRoleMenuCache(roleId, menuDataItemVos);
        //todo redis知试点 角色绑定放入缓存
    }

    /**
     * 更新角色资源路由缓存
     *
     * @param roleId          角色id
     * @param menuDataItemVos 资源路由
     */
    private void updateSysRoleMenuCache(int roleId, List<MenuDataItemVo> menuDataItemVos) {
        String cacheKey = CommonConstant.ROLE_MENU_PERMISSIONS;
        HashOperations<String, String, List<MenuDataItemVo>> hashOps = redisTemplate.opsForHash();
        hashOps.put(cacheKey, String.valueOf(roleId), menuDataItemVos);
    }

    /**
     * 删除角色资源路由缓存
     *
     * @param roleId 角色id
     */
    private void deleteSysRoleMenuCache(int roleId) {
        String cacheKey = CommonConstant.ROLE_MENU_PERMISSIONS;
        HashOperations<String, String, List<MenuDataItemVo>> hashOps = redisTemplate.opsForHash();
        hashOps.delete(cacheKey, String.valueOf(roleId));
    }

    /**
     * 从缓存中获取角色绑定的菜单
     *
     * 创建哈希结构的缓存
     * 把MenuDataItemVo缓存中的每个元素,转为String类型
     * 返回他 把它扁平化 过滤出不为空的元素
     * @param roleIds
     * @return
     */
    private List<MenuDataItemVo> listRoleMenuIdByCache(Set<Long> roleIds) {
        HashOperations<String, String, List<MenuDataItemVo>> hashOps = redisTemplate.opsForHash();
        List<List<MenuDataItemVo>> roleMenuIds = hashOps
                .multiGet(CommonConstant.ROLE_MENU_PERMISSIONS, //.multiGet()获取多个值
                roleIds.stream()
                        .map(String::valueOf)//把每个元素转为String类型
                        .collect(Collectors.toSet()));
        // 对结果进行处理，将 List<List<Integer>> 转为 List<Integer>
        // 用.filter过滤出不为空
        // 用.flatMap(嵌套列表扁平化为单一列表)
        return roleMenuIds.stream()
                .filter(p -> !CollectionUtils.isEmpty(p))
                .flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * 查询角色绑定的菜单列表
     *
     * @return
     */
    private List<MenuDataItemVo> listRoleBindMenu(int roleId, List<Integer> menuIds, List<SysMenu> sysMenus) {
        //过滤出所有子id,MAP把重复的父id集中(重复的数取一个元素) 省流:优化取出父id
        Set<Integer> parentIds = sysMenus.stream().filter(p -> (CommonConstant.ROLE_ADMIN == roleId || menuIds.contains(p.getId())) && p.getPid() > 0)
                .map(SysMenu::getPid).collect(Collectors.toSet());
        //查询所有子节点
        List<SysMenu> parentMenus = sysMenus.stream().filter(p -> parentIds.contains(p.getId())).collect(Collectors.toList());
        List<MenuDataItemVo> parentMenuDataItemVos = objectConvertor.toMenuDataItemVo(parentMenus);
        for (MenuDataItemVo parentMenuDataItemVo : parentMenuDataItemVos) {
            if (parentMenuDataItemVo.getLayout()) {
                parentMenuDataItemVo.setLayout(null);
            }
            //滤出符合父ID的菜单ID(找出子菜单)
            List<SysMenu> childMenus = sysMenus.stream().filter(p -> p.getPid().equals(parentMenuDataItemVo.getId())
                    && (CommonConstant.ROLE_ADMIN == roleId || menuIds.contains(p.getId())))
                    .collect(Collectors.toList());
            //转为返回参数 存入子菜单
            parentMenuDataItemVo.setRoutes(objectConvertor.toMenuDataItemVo(childMenus).stream().peek(p -> {
                if (p.getLayout()) {
                    p.setLayout(null);
                }
            }).collect(Collectors.toList()));
        }
        return parentMenuDataItemVos;
    }
    //todo 复杂点


    /**
     * 将某个角色绑定的资源设置到缓存中
     *
     * @param roleId
     */
    private void setSysRoleResourceCache(int roleId) {
        //查询绑定的资源id列表
        List<Integer> resourceIds = sysRoleBindResourceService.listBindResourceIdByRoleId(roleId);
        List<SysResource> sysResources = sysResourceService.listByIds(resourceIds);
        if (CollectionUtils.isEmpty(sysResources)) {
            updateSysRoleResourceCache(roleId, new HashSet<>());
        } else {
            updateSysRoleResourceCache(roleId,
                    sysResources.stream().map(SysResource::getPath).collect(Collectors.toSet()));
        }
    }

    /**
     * 更新角色资源路由缓存
     *
     * @param roleId       角色id
     * @param resourcePath 资源路由
     */
    private void updateSysRoleResourceCache(int roleId, Set<String> resourcePath) {
        String cacheKey = CommonConstant.ROLE_RESOURCE_PERMISSIONS;
        HashOperations<String, String, Set<String>> hashOps = redisTemplate.opsForHash();
        hashOps.put(cacheKey, String.valueOf(roleId), resourcePath);
    }

    /**
     * 删除角色资源路由缓存
     *
     * @param roleId 角色id
     */
    private void deleteSysRoleResourceCache(int roleId) {
        String cacheKey = CommonConstant.ROLE_RESOURCE_PERMISSIONS;
        HashOperations<String, String, Set<String>> hashOps = redisTemplate.opsForHash();
        hashOps.delete(cacheKey, String.valueOf(roleId));
    }
}
