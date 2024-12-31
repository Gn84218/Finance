package com.yang.finance.biz.service.impl;

import com.yang.common.exception.BizException;
import com.yang.common.service.TokenService;
import com.yang.finance.biz.config.ObjectConvertor;
import com.yang.finance.biz.domain.AssistCalculateCate;
import com.yang.finance.biz.dto.AdminDTO;
import com.yang.finance.biz.dto.form.CreateAssistCalculateCateForm;
import com.yang.finance.biz.dto.form.DelAssistCalculateCateForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateCateForm;
import com.yang.finance.biz.dto.vo.GetAssistCalculateCateVo;
import com.yang.finance.biz.dto.vo.ListCalculateCateVo;
import com.yang.finance.biz.enums.AssistCalculateCateCodeEnum;
import com.yang.finance.biz.enums.AssistCalculateCateLevelEnum;
import com.yang.finance.biz.mapper.AssistCalculateCateMapper;
import com.yang.finance.biz.service.AssistCalculateCateService;
import com.yang.mybatis.help.MyBatisWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.yang.finance.biz.domain.AssistCalculateCateField.*;

/**
 * 辅助核算类别
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AssistCalculateCateServiceImpl implements AssistCalculateCateService {
    final AssistCalculateCateMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;
    final ObjectMapper jsonMapper;

    /**
     * 添加辅助核算类别
     *
     * @param form
     * @return
     */
    @Override
    public boolean create(CreateAssistCalculateCateForm form) throws JsonProcessingException {
//        List<AssistCalculateCate> assistCalculateCateList = listByCodeOrName(form.getCode(), form.getName());
//        if (!CollectionUtils.isEmpty(assistCalculateCateList)) {
//            throw new BizException("辅助核算类别编码或名称已存在");
//        }

        //键入表单转换成数据库类型 初始化 创建内容
        AssistCalculateCate assistCalculateCate = objectConvertor.toAssistCalculateCate(form);
        assistCalculateCate.initDefault();
        assistCalculateCate.setLevel(AssistCalculateCateLevelEnum.CUSTOM.getCode());
        assistCalculateCate.setCode(AssistCalculateCateCodeEnum.CUSTOM.getCode());
        assistCalculateCate.setMemberId(tokenService.getThreadLocalUserId());
        assistCalculateCate.setUpdateMemberId(tokenService.getThreadLocalUserId());
        assistCalculateCate.setTenantId(tokenService.getThreadLocalTenantId());
        //自定义配置不为空 存入键入资料
        if (Objects.nonNull(form.getCustomerColumnConfigList())) {
            //把前端取得讯息 转换成json格式字符串存入
            assistCalculateCate.setCustomerColumn(jsonMapper.writeValueAsString(form.getCustomerColumnConfigList()));
        }
        return mapper.insert(assistCalculateCate) > 0;
    }

    /**
     * 删除辅助核算类别
     *
     * @param form
     * @return
     */
    @Override
    public boolean del(DelAssistCalculateCateForm form) {
        //根据键入名称 查询数据库
        AssistCalculateCate assistCalculateCate = getById2(form.getId());
        if (assistCalculateCate == null) {
            throw new BizException("辅助核算类别不存在");
        }
        if (assistCalculateCate.getUseCount() > 0) {
            throw new BizException("该辅助核算下面已经有辅助核算数据不能删除");
        }
        MyBatisWrapper<AssistCalculateCate> wrapper = new MyBatisWrapper<>();
        wrapper.updateConcat(setName("_del_" + form.getId()))//拼接字段
                .update(setDelFlag(true))
                .update(setUpdateMemberId(tokenService.getThreadLocalUserId()))
                .update(setUpdateTime(new Date()))
                .whereBuilder()
                .andEq(setId(form.getId()))
                .andEq(Level, AssistCalculateCateLevelEnum.CUSTOM.getCode())
                .andEq(setTenantId(tokenService.getThreadLocalTenantId()))
                .andEq(setDelFlag(false))
                .andEq(UseCount, 0);
        if (mapper.updateField(wrapper) == 0) {
            throw new BizException("删除失败");
        }
        return true;
    }

    /**
     * 修改辅助核算类别
     *
     * @param form
     * @return
     */
    @Override
    public boolean update(UpdateAssistCalculateCateForm form) throws JsonProcessingException {
        AssistCalculateCate assistCalculateCate = getById2(form.getId());
        if (assistCalculateCate == null) {
            throw new BizException("辅助核算类别不存在");
        }
        MyBatisWrapper<AssistCalculateCate> wrapper = new MyBatisWrapper<>();
        wrapper.update(setName(form.getName()))
                .update(setUpdateMemberId(tokenService.getThreadLocalUserId()))
                .update(setUpdateTime(new Date()))
                .whereBuilder()
                .andEq(setId(form.getId()))
                .andEq(Level, AssistCalculateCateLevelEnum.CUSTOM.getCode())
                .andEq(setTenantId(tokenService.getThreadLocalTenantId()))
                .andEq(DelFlag, false);
        //自定义列表 不为空 更新数据
        if (Objects.nonNull(form.getCustomerColumnConfigList())) {
            wrapper.update(setCustomerColumn(jsonMapper.writeValueAsString(form.getCustomerColumnConfigList())));
        }
        if (mapper.updateField(wrapper) == 0) {
            throw new BizException("修改失败");
        }
        return true;
    }

    /**
     * 查询辅助核算类别信息
     *键入名称查询
     * @param id
     * @return
     */
    @Override
    public GetAssistCalculateCateVo getById(long id) throws JsonProcessingException {
        MyBatisWrapper<AssistCalculateCate> wrapper = new MyBatisWrapper<>();
        wrapper
                .select(Id, Name, CustomerColumn, Level, Code)
                .whereBuilder()
                .andEq(setId(id))
                .andEq(setTenantId(tokenService.getThreadLocalTenantId()))
                .andEq(setDelFlag(false));
        AssistCalculateCate assistCalculateCate = mapper.get(wrapper);
        if (assistCalculateCate == null) {
            throw new BizException("辅助核算类别不存在");
        }
        GetAssistCalculateCateVo getAssistCalculateCateVo = objectConvertor.toGetAssistCalculateCateVo(assistCalculateCate);
        //返回参数 存入反序列化(查询到数据库的自定义键入信息) ==数据库自定义字段转成前端键入需要
        getAssistCalculateCateVo.setCustomerColumnConfigList(jsonMapper.readValue(assistCalculateCate.getCustomerColumn(),
                new TypeReference<List<CreateAssistCalculateCateForm.CustomerColumnConfig>>() {
                }));
        return getAssistCalculateCateVo;
    }

    /**
     * 查询辅助核算列表
     *
     * @return
     */
    @Override
    public List<ListCalculateCateVo> list() {
        MyBatisWrapper<AssistCalculateCate> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Name, Level, Code)
                .whereBuilder()
                .andEq(DelFlag, false)
                .andEq(TenantId, tokenService.getThreadLocalTenantId());
        wrapper
                .orderByAsc(Level, Sort, CreateTime);
        List<AssistCalculateCate> assistCalculateCateList = mapper.list(wrapper);
        return objectConvertor.toListCalculateCateVos(assistCalculateCateList);
    }

    /**
     * 查询辅助核算列表
     *
     * @return
     */
    @Override
    public List<AssistCalculateCate> list(Set<Long> ids) {
        MyBatisWrapper<AssistCalculateCate> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Name, Level, Code)
                .whereBuilder()
                .andEq(DelFlag, false)
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andIn(Id, ids);
        wrapper
                .orderByAsc(Level, Sort, CreateTime);
        return mapper.list(wrapper);
    }

    /**
     * 查询辅助核算类别信息
     *
     * @param id
     * @return
     */
    @Override
    public AssistCalculateCate getById2(long id) {
        MyBatisWrapper<AssistCalculateCate> wrapper = new MyBatisWrapper<>();
        wrapper
                .select(Id, Name, CustomerColumn, Level, Code, UseCount)
                .whereBuilder()
                .andEq(setId(id))
                .andEq(setTenantId(tokenService.getThreadLocalTenantId()))
                .andEq(setDelFlag(false));
        return mapper.get(wrapper);
    }

    /**
     * 增加辅助核算类别使用计数
     *
     * @param id
     * @param count
     * @return
     */
    @Override
    public boolean addUseCount(long id, int count) {
        MyBatisWrapper<AssistCalculateCate> wrapper = new MyBatisWrapper<>();
        wrapper.updateIncr(UseCount, count)//自增
                .whereBuilder()
                .andEq(Id, id);
        if (mapper.updateField(wrapper) == 0) {
            throw new BizException("增加辅助核算类别使用计数异常");
        }
        return true;
    }

    /**
     * 减少辅助核算类别使用计数
     *
     * @param id
     * @param count
     * @return
     */
    @Override
    public boolean deductUseCount(long id, int count) {
        MyBatisWrapper<AssistCalculateCate> wrapper = new MyBatisWrapper<>();
        wrapper.updateDecr(UseCount, count)//自减 用法(n,1)等于n-1
                .whereBuilder()
                .andEq(Id, id)
                .andGTE(setUseCount(count));
        if (mapper.updateField(wrapper) == 0) {
            throw new BizException("增加辅助核算类别使用计数异常");
        }
        return true;
    }

    /**
     * 增加辅助核算类别使用计数
     *
     * @param ids
     * @return
     */
    @Override
    public boolean addUseCount(Set<Long> ids) {
        MyBatisWrapper<AssistCalculateCate> wrapper = new MyBatisWrapper<>();
        wrapper.updateIncr(UseCount, 1)
                .whereBuilder()
                .andIn(Id, ids)
                .andEq(TenantId, tokenService.getThreadLocalTenantId());
        int updateRows = mapper.updateField(wrapper);
        if (ids.size() != updateRows) {
            throw new BizException("增加辅助核算类别使用计数异常");
        }
        return true;
    }

    /**
     * 扣减辅助核算类别使用计数
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deductUseCount(Set<Long> ids) {
        MyBatisWrapper<AssistCalculateCate> wrapper = new MyBatisWrapper<>();
        wrapper.updateDecr(UseCount, 1)
                .whereBuilder()
                .andIn(Id, ids)
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andGT(UseCount, 0);
        int updateRows = mapper.updateField(wrapper);
        if (ids.size() != updateRows) {
            throw new BizException("增加辅助核算类别使用计数异常");
        }
        return true;
    }
}
