package com.yang.finance.biz.service.impl;

import com.yang.common.exception.BizException;
import com.yang.common.service.TokenService;
import com.yang.common.util.DateUtil;
import com.yang.finance.biz.config.ObjectConvertor;
import com.yang.finance.biz.constant.RedisKeyConstant;
import com.yang.finance.biz.domain.SysMenu;
import com.yang.finance.biz.dto.AdminDTO;
import com.yang.finance.biz.dto.form.CreateMenuForm;
import com.yang.finance.biz.dto.form.DelMenuForm;
import com.yang.finance.biz.dto.form.UpdateMenuForm;
import com.yang.finance.biz.dto.vo.GetMenuByIdVo;
import com.yang.finance.biz.dto.vo.ListTreeMenuVo;
import com.yang.finance.biz.dto.vo.ListTreeSelectMenuVo;
import com.yang.finance.biz.mapper.SysMenuMapper;
import com.yang.finance.biz.service.SysMenuService;
import com.yang.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.yang.finance.biz.domain.SysMenuField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {
    final SysMenuMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;
    final RedisTemplate<String, Object> redisTemplate;
    final RedissonClient redissonClient;

    /**
     * 获取树形菜单
     * 查询单笔资料
     * @return
     */
    @Override
    //獲取 菜單 數據庫name 键入的是数据库名
    public List<ListTreeMenuVo> listTreeMenu(String name) {
        //輸入欄位不是空的話
        if (Strings.isNotBlank(name)) {
            //取出键入名称查询数据库
            List<SysMenu> sysMenus = list(name);
            //转换给前端所要参数
            return objectConvertor.toListTreeMenuVo(sysMenus);
        } else {
            //查出所有数据
            return listTreeMenuByMenuIds();
        }
    }

    /**
     * 获取树形菜单
     *
     * 查出所有父类 还有子类 资料
     * @return
     */
    @Override
    public List<ListTreeMenuVo> listTreeMenuByMenuIds() {
        //返回参数 为空
        List<ListTreeMenuVo> result = null;
        //把所有菜单资料查出来
        List<SysMenu> list = list();
        //如果查询为空 返回null
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        //取出第一层 ID信息(父ID)
        //不为空的话 查询出所有父级菜单 把父ID为0的值(只要为0都是父类) 集中装到一个集合里
        List<SysMenu> parentMenuList = list.stream().filter(p -> p.getPid() == 0)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(parentMenuList)) {
            return null;
        }
        //把查询到的 第一层讯息(父ID) 转换为前端要的参数
        result = objectConvertor.toListTreeMenuVo(parentMenuList);
        //历遍 第一层(父类信息)返回参数
        for (ListTreeMenuVo listTreeMenuVo : result) {
            //查出所有父类下的子ID 资讯
            //过滤出相同 数据库父ID和返回参数(主ID)相同,装回集合里
            List<SysMenu> childMenuList = list.stream()
                    .filter(p -> Objects.equals(p.getPid(), listTreeMenuVo.getId()))
                    .collect(Collectors.toList());
            //如果完全没有子类资讯
            if (CollectionUtils.isEmpty(childMenuList)) {
                //跳过剩余的循环
                continue;
            }
            //把子类资讯 转换,存入 为返回参数
            listTreeMenuVo.setChildren(objectConvertor.toListTreeMenuVo(childMenuList));
        }
        return result;
    }

    /**
     * 获取树形选择菜单
     *
     * 跟listTreeMenu 逻辑相同
     * @return
     */
    @Override
    public List<ListTreeSelectMenuVo> listTreeSelectMenu() {
        List<ListTreeSelectMenuVo> result = new ArrayList<>();
        ListTreeSelectMenuVo rootMenu = new ListTreeSelectMenuVo();
        rootMenu.setId(0);
        rootMenu.setValue("0");
        rootMenu.setTitle("根菜单");
        result.add(rootMenu);
        List<SysMenu> list = list();
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }

        List<SysMenu> parentMenuList = list.stream().filter(p -> p.getPid() == 0)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(parentMenuList)) {
            return result;
        }
        rootMenu.setChildren(objectConvertor.toListTreeSelectMenuVo(parentMenuList));
        for (ListTreeSelectMenuVo listTreeSelectMenuVo : rootMenu.getChildren()) {
            List<SysMenu> childMenuList = list.stream().filter(p -> Objects.equals(p.getPid(), listTreeSelectMenuVo.getId()))
                    .collect(Collectors.toList());
            listTreeSelectMenuVo.setChildren(objectConvertor.toListTreeSelectMenuVo(childMenuList));
        }
        return result;
    }

    @Override
    public GetMenuByIdVo getMenuById(int id) {
        //获取数据库参数 转为返回参数
        SysMenu sysMenu = get(id);
        return objectConvertor.toGetMenuByIdVo(sysMenu);
    }

    /**
     * 创建菜单
     *
     * @param form
     * @return
     */
    @Override
    public boolean create(CreateMenuForm form) {
        //分布式锁
        RLock rLock = redissonClient.getLock(RedisKeyConstant.CHANGE_MENU_LOCK);
        try {
            rLock.lock();
            //检测父级菜单 因为只有两层所以必为o才不会报错
            int parentLevel = checkParent(form.getPid());
            //更新菜单顺序
            updateSort(form.getPid(), form.getSort());
            //dto转换 返回参数
            SysMenu sysMenu = objectConvertor.toSysMenu(form);
            sysMenu.initDefault();
            //设定深度为1 子菜单深度都为1 因目前开发只用到两层
            sysMenu.setNodePath(parentLevel + 1);
            //取的用户并更新
            sysMenu.setMemberId(tokenService.getThreadLocalUserId());
            sysMenu.setUpdateMemberId(tokenService.getThreadLocalUserId());
            mapper.insert(sysMenu);
            return true;
        } catch (Exception ex) {
            throw new BizException(ex.getMessage(), ex);
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 修改菜单
     *
     * @param form
     * @return
     */
    @Override
    public boolean update(UpdateMenuForm form) {
        SysMenu sysMenu = get(form.getId());
        if (sysMenu == null) {
            throw new BizException("菜单不存在");
        }
        RLock rLock = redissonClient.getLock(RedisKeyConstant.CHANGE_MENU_LOCK);
        try {
            rLock.lock();
            //更新菜单顺序
            updateSort(sysMenu.getPid(), form.getSort());
            MyBatisWrapper<SysMenu> wrapper = new MyBatisWrapper<>();
            wrapper
                    .update(Name, form.getName())
                    .update(setPath(form.getPath()))
                    .update(setComponent(form.getComponent()))
                    .update(setIcon(form.getIcon()))
                    .update(setLayout(form.getLayout()))
                    .update(setHideInMenu(form.getHideInMenu()))
                    .update(setRedirect(form.getRedirect()))
                    .update(setSort(form.getSort()))
                    .whereBuilder()
                    //修改条件
                    .andEq(setId(form.getId()))
                    .andEq(setDelFlag(false));
            return mapper.updateField(wrapper) > 0;
        } catch (Exception ex) {
            throw new BizException(ex.getMessage(), ex);
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 删除菜单
     *
     * @param form
     * @return
     */
    @Override
    public boolean del(DelMenuForm form) {
        RLock rLock = redissonClient.getLock(RedisKeyConstant.CHANGE_MENU_LOCK);
        try {
            rLock.lock();
            if (countByPid(form.getId()) > 0) {
                throw new BizException("该菜单下有子菜单不能直接删除");
            }
            MyBatisWrapper<SysMenu> wrapper = new MyBatisWrapper<>();
            wrapper.update(setDelFlag(true))//更新已删除
                    .update(setUpdateMemberId(tokenService.getThreadLocalUserId()))//谁更新的
                    .update(setUpdateTime(DateUtil.getSystemTime()))//更新时间
                    .whereBuilder()
                    .andEq(setId(form.getId()));
            return mapper.updateField(wrapper) > 0;
        } catch (Exception ex) {
            throw new BizException(ex.getMessage(), ex);
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 根据id查询菜单列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysMenu> listByIds(List<Integer> ids) {
        MyBatisWrapper<SysMenu> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Path)
                .whereBuilder()
                .andIn(Id, ids)
                .andEq(DelFlag, false);
        return mapper.list(wrapper);
    }

    /**
     * 查询菜单列表
     *
     * @return
     */
    @Override
    public List<SysMenu> list() {
        MyBatisWrapper<SysMenu> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Pid, Name, Path, Component, Icon, Layout, HideInMenu, Redirect, Sort, NodePath)
                .whereBuilder()
                .andEq(setDelFlag(false))
                .andEq(setDisable(false));
        wrapper.orderByAsc(Sort);
        return mapper.list(wrapper);
    }

    /**
     * 查询菜单列表
     *
     * 所有的都查詢出來
     * @return
     */
    private List<SysMenu> list(String name) {
        MyBatisWrapper<SysMenu> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Pid, Name, Path, Component, Icon, Layout, HideInMenu, Redirect, Sort, NodePath)
                .whereBuilder().andEq(setDelFlag(false))
                .andEq(setDisable(false))
                //模糊查詢name
                .andLike(Name, "%" + name + "%");
        //排序按照(Sort)
        wrapper.orderByAsc(Sort);
        return mapper.list(wrapper);
    }

    /**
     * 获取菜单
     *
     * @param id
     * @return
     */
    private SysMenu get(int id) {
        MyBatisWrapper<SysMenu> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Pid, Name, Path, Component, Icon, Layout, HideInMenu, Redirect, Sort, NodePath)
                .whereBuilder()
                .andEq(setId(id))
                .andEq(setDelFlag(false))
                .andEq(setDisable(false));
        return mapper.get(wrapper);
    }

    /**
     * 统计子菜单数量
     *
     * @param pid
     * @return
     */
    private int countByPid(int pid) {
        MyBatisWrapper<SysMenu> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Pid, Name, Path, Component, Icon, Layout, HideInMenu, Redirect, Sort)
                .whereBuilder()
                .andEq(setPid(pid))
                .andEq(setDelFlag(false))
                .andEq(setDisable(false));
        return mapper.count(wrapper);
    }


    /**
     * 更新菜单顺序
     *
     * 因为是插入 所以在sort上订好要的数字 比如1后面所有2345号都加上1就插进去了
     * 根据父ID排序
     *
     * @param pid
     * @param sort
     * @return
     */
    private int updateSort(int pid, int sort) {
        MyBatisWrapper<SysMenu> wrapper = new MyBatisWrapper<>();
        wrapper
                .updateIncr(setSort(1))
                .whereBuilder()
                .andEq(setPid(pid))
                .andGTE(setSort(sort))
                .andEq(setDelFlag(false));
        return mapper.updateField(wrapper);
    }

    /**
     * 检测父级菜单 并返回level级别
     * 就是返回节点深度
     * @param pid
     */
    private int checkParent(int pid) {
        if (pid == 0) {
            return 0;
        }
        SysMenu sysMenu = get(pid);
        if (sysMenu == null) {
            throw new BizException("父级菜单不存在");
        }
        //确保父菜单 PID为0
        //只有两层菜单 父菜单统一为0 大于的话必定遍为三层以上
        if (sysMenu.getPid() > 0) {
            throw new BizException("不可以创建三级菜单");
        }
        return sysMenu.getNodePath();
    }
}
