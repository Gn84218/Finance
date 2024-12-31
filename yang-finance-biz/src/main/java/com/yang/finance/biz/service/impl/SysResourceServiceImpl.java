package com.yang.finance.biz.service.impl;

import com.yang.common.exception.BizException;
import com.yang.common.service.TokenService;
import com.yang.finance.biz.config.ObjectConvertor;
import com.yang.finance.biz.constant.RedisKeyConstant;
import com.yang.finance.biz.domain.SysResource;
import com.yang.finance.biz.dto.AdminDTO;
import com.yang.finance.biz.dto.form.CreateSysResourceForm;
import com.yang.finance.biz.dto.form.DelSysResourceForm;
import com.yang.finance.biz.dto.form.ListSysResourceForm;
import com.yang.finance.biz.dto.form.UpdateSysResourceForm;
import com.yang.finance.biz.dto.vo.GetSysResourceVo;
import com.yang.finance.biz.dto.vo.ListSysResourceVo;
import com.yang.finance.biz.mapper.SysResourceMapper;
import com.yang.finance.biz.service.SysResourceService;
import com.yang.mybatis.help.Criteria;
import com.yang.mybatis.help.MyBatisWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.yang.finance.biz.domain.SysResourceField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysResourceServiceImpl implements SysResourceService {
    final SysResourceMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;
    final ObjectMapper jsonMapper;
    final RedisTemplate<String, Object> redisTemplate;
    final RedissonClient redissonClient;

    /**
     * 创建资源
     *
     * @param form
     * @return
     */
    @Override
    public boolean create(CreateSysResourceForm form) {
        SysResource saveSysResource = objectConvertor.toSysResource(form);
        //初始返回参数默认值,存入ID,更新ID,上锁
        saveSysResource.initDefault();
        saveSysResource.setMemberId(tokenService.getThreadLocalUserId());
        saveSysResource.setUpdateMemberId(tokenService.getThreadLocalUserId());
        RLock rLock = redissonClient.getLock(RedisKeyConstant.CHANGE_RESOURCE_LOCK);
        try {
            rLock.lock();
            SysResource sysResource = getByPath(form.getPath());
            if (sysResource != null) {
                throw new BizException("该资源路径已存在");
            }
            //检查是否有上级资源(当为子资源时)
            if (form.getPid() > 0) {
                //找出子资源的父级资源ID (节点深度为0)
                SysResource parentSysResource = getById(form.getPid());
                if (parentSysResource == null) {
                    throw new BizException("上级资源不存在");
                }
                if (parentSysResource.getPid() > 0) {
                    throw new BizException("不能创建三级资源");
                }
                //检查父,子路径是否正确
                checkPath(parentSysResource.getPath(), form.getPath());
            } else {
                //如果父路径不为空 格式又正确,子资源又是空的 返回ture
                checkPath(form.getPath(), null);
            }
            //插入数据
            return mapper.insert(saveSysResource) > 0;
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 修改资源
     *
     * @param form
     * @return
     */
    @Override
    public boolean update(UpdateSysResourceForm form) {
        RLock rLock = redissonClient.getLock(RedisKeyConstant.CHANGE_RESOURCE_LOCK);
        try {
            rLock.lock();
            //键入ID匹配数据库ID
            SysResource sysResource = getById(form.getId());
            if (sysResource == null) {
                throw new BizException("资源不存在");
            }
            SysResource sysResourceByPath = getByPath(form.getPath());
            //路径不为空 且该路径 键入ID和数据库ID不能相同
            if (sysResourceByPath != null && !sysResourceByPath.getId().equals(form.getId())) {
                throw new BizException("该资源路径已存在");
            }
            //如果不是父资源
            if (sysResource.getPid() > 0) {
                //取出父资源
                SysResource parentSysResource = getById(sysResource.getPid());
                if (parentSysResource == null) {
                    throw new BizException("上级资源不存在");
                }
                //子资源不能再有子资源
                if (parentSysResource.getPid() > 0) {
                    throw new BizException("不能创建三级资源");
                }

                checkPath(parentSysResource.getPath(), form.getPath());
            } else {
                //如果为父资源(深度为0),做校验
                checkPath(form.getPath(), null);
            }
            //检查完成 更新数据库
            MyBatisWrapper<SysResource> wrapper = new MyBatisWrapper<>();
            wrapper.update(Name, form.getName())
                    .update(Path, form.getPath())
                    .update(UpdateMemberId, tokenService.getThreadLocalUserId())
                    .whereBuilder()
                    .andEq(Id, form.getId());
            return mapper.updateField(wrapper) > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 删除资源
     *
     * @param form
     * @return
     */
    @Override
    public boolean del(DelSysResourceForm form) {
        RLock rLock = redissonClient.getLock(RedisKeyConstant.CHANGE_RESOURCE_LOCK);
        try {
            rLock.lock();
            //取出父ID值
            int count = countByPid(form.getId());
            if (count > 0) {
                throw new BizException("该资源下面还有子资源，不能删除");
            }
            //更新数据库资料 路径,修改用户ID,已删除,
            MyBatisWrapper<SysResource> wrapper = new MyBatisWrapper<>();
            wrapper.updateConcat(Path, "@DEL_" + form.getId())//在所有路径(Path)名后加上值(value)
                    .update(DelFlag, true)
                    .update(UpdateMemberId, tokenService.getThreadLocalUserId())
                    .whereBuilder()
                    .andEq(Id, form.getId());
            return mapper.updateField(wrapper) > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 获取资源
     *
     * @param id
     * @return
     */
    @Override
    public GetSysResourceVo get(int id) {
        SysResource sysResource = getById(id);
        if (sysResource == null) {
            throw new BizException("资源不存在");
        }
        GetSysResourceVo result = objectConvertor.toGetSysResourceVo(sysResource);
        result.setParentName("无上级资源");
        if (sysResource.getPid() > 0) {
            //根据父ID 找出ID 判断有无上级资源
            SysResource parentSysResource = getById(sysResource.getPid());
            if (parentSysResource == null) {
                throw new BizException("上级资源不存在");
            }
            result.setParentName(parentSysResource.getName());
        }
        return result;
    }

    /**
     * 获取资源列表
     *
     * @param form
     * @return
     */
    @Override
    public List<ListSysResourceVo> list(ListSysResourceForm form) {
        MyBatisWrapper<SysResource> wrapper = new MyBatisWrapper<>();
        //orm操作符构造查询条件
        Criteria<SysResource> criteria = wrapper.select(Id, Name, Path, Pid)
                .whereBuilder()
                .andEq(DelFlag, false);
        //键入名称栏位不为空 模糊查询
        if (Strings.isNotBlank(form.getName())) {
            criteria.andLike(Name, "%" + form.getName() + "%");
        }
        //键入路径栏位不为空 模糊查询
        if (Strings.isNotBlank(form.getPath())) {
            criteria.andLike(Path, "%" + form.getPath() + "%");
        }
        //取出所有数据库,转成前端返回参数,过滤出所有一级资源
        List<SysResource> sysResources = mapper.list(wrapper);
        List<ListSysResourceVo> listSysResourceVos = objectConvertor.toListSysResourceVo(sysResources);
        List<ListSysResourceVo> parentList = listSysResourceVos.stream()
                .filter(p -> p.getPid() == 0).collect(Collectors.toList());
        //如果没有一级资源 返回所有返回参数
        if (CollectionUtils.isEmpty(parentList)) {
            return listSysResourceVos;
        }
        //找出所有子资源
        //有的话,遍历所有一级资源(遍历所有parentList的parent对象)
        for (ListSysResourceVo parent : parentList) {
            List<ListSysResourceVo> childList = listSysResourceVos.stream()
                    .filter(p -> Integer.compare(parent.getId(), p.getPid()) == 0)//过滤出一级资源ID等于二及资源父ID的值
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(childList)) {
                parent.setChildren(childList);
            }
        }

        return parentList;
    }


    /**
     * 根据id列表查询数量
     *
     * @param ids
     * @return
     */
    @Override
    public int countByIds(List<Integer> ids) {
        MyBatisWrapper<SysResource> wrapper = new MyBatisWrapper<>();
        wrapper.whereBuilder()
                .andIn(Id, ids)
                .andEq(DelFlag, false);
        return mapper.count(wrapper);
    }

    /**
     * 根据id查询资源列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysResource> listByIds(List<Integer> ids) {
        MyBatisWrapper<SysResource> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Path)
                .whereBuilder()
                .andIn(Id, ids)
                .andEq(DelFlag, false);
        return mapper.list(wrapper);
    }

    /**
     * 根据id获取资源
     *
     * @param id
     * @return
     */
    private SysResource getById(int id) {
        MyBatisWrapper<SysResource> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Pid, Name, Path, NodePath)
                .whereBuilder()
                .andEq(Id, id)
                .andEq(DelFlag, false);
        return mapper.get(wrapper);
    }

    /**
     * 根据路径获取资源
     *
     * 键入路径和数据库的一致
     * @param path
     * @return
     */
    private SysResource getByPath(String path) {
        MyBatisWrapper<SysResource> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Pid, Name, Path, NodePath)
                .whereBuilder()
                .andEq(Path, path);
        return mapper.get(wrapper);
    }

    /**
     * 根据pid统计子节点数量
     *
     * @param pid
     * @return
     */
    private int countByPid(int pid) {
        MyBatisWrapper<SysResource> wrapper = new MyBatisWrapper<>();
        wrapper.whereBuilder()
                .andEq(Pid, pid)
                .andEq(DelFlag, false);
        return mapper.count(wrapper);
    }

    /**
     * 检查path是否合法
     *
     * 分别检查 父路径和子路径 格式是否正确
     *
     * @param parentPath
     * @param childPath
     * @return
     */
    private boolean checkPath(String parentPath, String childPath) {
        if (parentPath == null) {
            throw new BizException("资源路径格式不正确");
        }
        // 检查路径格式
        String parentRegex = "^/[a-zA-Z0-9_*-]+(?:/[a-zA-Z0-9_*-]+)*/\\*+$";
        //如果不符合格式
        if (!parentPath.matches(parentRegex)) {
            throw new BizException("资源路径格式不正确");
        }
        //
        if (childPath == null) {
            return true;
        }
        // 获取/*之前的内容(父路径)
        // 及第0号索引位置 到最后一个/加上1个索引位(/*)
        String prefix = parentPath.substring(0, parentPath.lastIndexOf("/") + 1);
        //如果字符串不是已指定方式  返回异常
        if (!childPath.startsWith(prefix)) {
            throw new BizException("资源路径格式不正确");
        }
        String childRegex = "^/[a-zA-Z0-9_*-]+(?:/[a-zA-Z0-9_*-]+)*$";

        if (!childPath.matches(childRegex)) {
            throw new BizException("资源路径格式不正确");
        }
        //如果都没异常出错 返回true
        return true;
    }
}
