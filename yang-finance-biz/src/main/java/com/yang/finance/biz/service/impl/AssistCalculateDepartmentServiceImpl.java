package com.yang.finance.biz.service.impl;

import com.yang.common.exception.BizException;
import com.yang.common.service.TokenService;
import com.yang.finance.biz.config.ObjectConvertor;
import com.yang.finance.biz.domain.AssistCalculateDepartment;
import com.yang.finance.biz.dto.AdminDTO;
import com.yang.finance.biz.dto.form.CreateAssistCalculateDepartmentForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateDepartmentForm;
import com.yang.finance.biz.dto.vo.GetAssistCalculateDepartmentVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateDepartmentVo;
import com.yang.finance.biz.enums.AssistCalculateCateCodeEnum;
import com.yang.finance.biz.mapper.AssistCalculateDepartmentMapper;
import com.yang.finance.biz.service.AssistCalculateHandleService;
import com.yang.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.yang.finance.biz.domain.AssistCalculateDepartmentField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssistCalculateDepartmentServiceImpl implements AssistCalculateHandleService {
    final AssistCalculateDepartmentMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;

    @Override
    public AssistCalculateCateCodeEnum getAssistCalculateCateCodeEnum() {
        return AssistCalculateCateCodeEnum.DEPARTMENT;
    }

    /**
     * 添加职员辅助核算
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Object obj) {
        CreateAssistCalculateDepartmentForm form = (CreateAssistCalculateDepartmentForm) obj;
        AssistCalculateDepartment assistCalculateDepartment = objectConvertor.toAssistCalculateDepartment(form);
        assistCalculateDepartment.initDefault();
        assistCalculateDepartment.setMemberId(tokenService.getThreadLocalUserId());
        assistCalculateDepartment.setUpdateMemberId(tokenService.getThreadLocalUserId());
        assistCalculateDepartment.setTenantId(tokenService.getThreadLocalTenantId());

        return mapper.insert(assistCalculateDepartment) > 0;
    }

    /**
     * 查询客户辅助核算客户列表
     *
     * @return
     */
    @Override
    public List<ListAssistCalculateDepartmentVo> listByAssistCalculateSummaryIds(List<Long> assistCalculateSummaryIds) {
        MyBatisWrapper<AssistCalculateDepartment> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Manager, Phone, StartDate, RevokeDate, Disable, AssistCalculateSummaryId)
                .whereBuilder()
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false)
                .andIn(AssistCalculateSummaryId, assistCalculateSummaryIds);
        List<AssistCalculateDepartment> assistCalculates = mapper.list(wrapper);
        return objectConvertor.toAssistCalculateDepartmentVo(assistCalculates);
    }

    /**
     * 修改客户辅助核算
     *
     * @param obj
     * @return
     */
    @Override
    public boolean update(Object obj) {
        UpdateAssistCalculateDepartmentForm form = (UpdateAssistCalculateDepartmentForm) obj;
        MyBatisWrapper<AssistCalculateDepartment> wrapper = new MyBatisWrapper<>();
        wrapper.update(UpdateTime, new Date())
                .update(UpdateMemberId, tokenService.getThreadLocalUserId())
                .update(Manager, form.getManager())
                .update(Phone, form.getPhone())
                .update(StartDate, form.getStartDate())
                .update(RevokeDate, form.getRevokeDate())
                .whereBuilder()
                .andEq(AssistCalculateSummaryId, form.getId())
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false);

        if (mapper.updateField(wrapper) == 0) {
            throw new BizException("修改失败");
        }
        return true;
    }

    /**
     * 获取客户辅助核算详情
     *
     * @param id
     * @return
     */
    @Override
    public GetAssistCalculateDepartmentVo get(long id) {
        AssistCalculateDepartment assistCalculateDepartment = getById(id);
        GetAssistCalculateDepartmentVo result = objectConvertor.toGetAssistCalculateDepartmentVo(assistCalculateDepartment);
        return result;
    }

    /**
     * 查询职员辅助核算明细
     *
     * @param id
     * @return
     */
    private AssistCalculateDepartment getById(long id) {
        MyBatisWrapper<AssistCalculateDepartment> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Manager, StartDate, RevokeDate, Phone)
                .whereBuilder()
                .andEq(AssistCalculateSummaryId, id)
                .andEq(DelFlag, false)
                .andEq(TenantId, tokenService.getThreadLocalTenantId());
        return mapper.get(wrapper);
    }
}
