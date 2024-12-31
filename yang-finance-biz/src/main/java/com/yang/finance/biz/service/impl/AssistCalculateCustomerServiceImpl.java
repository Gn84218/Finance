package com.yang.finance.biz.service.impl;

import com.yang.common.exception.BizException;
import com.yang.common.service.TokenService;
import com.yang.finance.biz.config.ObjectConvertor;
import com.yang.finance.biz.domain.AssistCalculateCustomer;
import com.yang.finance.biz.dto.AdminDTO;
import com.yang.finance.biz.dto.form.CreateAssistCalculateCustomerForm;
import com.yang.finance.biz.dto.form.UpdateAssistCalculateCustomerForm;
import com.yang.finance.biz.dto.vo.GetAssistCalculateCustomerVo;
import com.yang.finance.biz.dto.vo.ListAssistCalculateCustomerVo;
import com.yang.finance.biz.enums.AssistCalculateCateCodeEnum;
import com.yang.finance.biz.mapper.AssistCalculateCustomerMapper;
import com.yang.finance.biz.service.AssistCalculateHandleService;
import com.yang.finance.biz.service.SysRegionService;
import com.yang.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.yang.finance.biz.domain.AssistCalculateCustomerField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssistCalculateCustomerServiceImpl implements AssistCalculateHandleService {
    final AssistCalculateCustomerMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;
    final SysRegionService sysRegionService;

    /**
     * 返回客户类别
     *
     * @return
     */
    @Override
    public AssistCalculateCateCodeEnum getAssistCalculateCateCodeEnum() {
        return AssistCalculateCateCodeEnum.CUSTOMER;
    }

    /**
     * 添加客户辅助核算
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Object obj) {
        CreateAssistCalculateCustomerForm form = (CreateAssistCalculateCustomerForm) obj;
        //检测地区编码是否合法
        if (Strings.isNotBlank(form.getCountyCode())) {
            sysRegionService.checkRegionCode(form.getProvinceCode(), form.getCityCode(), form.getCountyCode());
        }
        AssistCalculateCustomer assistCalculateCustomer = objectConvertor.toAssistCalculateCustomer(form);
        assistCalculateCustomer.initDefault();
        assistCalculateCustomer.setMemberId(tokenService.getThreadLocalUserId());
        assistCalculateCustomer.setUpdateMemberId(tokenService.getThreadLocalUserId());
        assistCalculateCustomer.setTenantId(tokenService.getThreadLocalTenantId());

        return mapper.insert(assistCalculateCustomer) > 0;
    }

    /**
     * 查询客户辅助核算客户列表
     *
     * @return
     */
    @Override
    public List<ListAssistCalculateCustomerVo> listByAssistCalculateSummaryIds(List<Long> assistCalculateSummaryIds) {
        MyBatisWrapper<AssistCalculateCustomer> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, CustomerCate, Address, Contacts,
                Phone, UnifiedSocialCreditCode, Disable, AssistCalculateSummaryId)
                .whereBuilder()
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false)
                .andIn(AssistCalculateSummaryId, assistCalculateSummaryIds);
        List<AssistCalculateCustomer> assistCalculates = mapper.list(wrapper);
        return objectConvertor.toListAssistCalculateCustomerVo(assistCalculates);
    }

    /**
     * 修改客户辅助核算
     *
     * @param obj
     * @return
     */
    @Override
    public boolean update(Object obj) {
        UpdateAssistCalculateCustomerForm form = (UpdateAssistCalculateCustomerForm) obj;
        //检测地区编码是否合法
        if (Strings.isNotBlank(form.getCountyCode())) {
            sysRegionService.checkRegionCode(form.getProvinceCode(), form.getCityCode(), form.getCountyCode());
        }
        MyBatisWrapper<AssistCalculateCustomer> wrapper = new MyBatisWrapper<>();
        wrapper.update(UpdateTime, new Date())
                .update(UpdateMemberId, tokenService.getThreadLocalUserId())
                .update(UnifiedSocialCreditCode, form.getUnifiedSocialCreditCode())
                .update(CustomerCate, form.getCustomerCate())
                .update(ProvinceCode, form.getProvinceCode())
                .update(CityCode, form.getCityCode())
                .update(CountyCode, form.getCountyCode())
                .update(Address, form.getAddress())
                .update(Contacts, form.getContacts())
                .update(Phone, form.getPhone())
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
     * @param assistCalculateSummaryId
     * @return
     */
    @Override
    public GetAssistCalculateCustomerVo get(long assistCalculateSummaryId) {
        MyBatisWrapper<AssistCalculateCustomer> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, CustomerCate, UnifiedSocialCreditCode, ProvinceCode,
                CityCode, CountyCode, Address, Contacts, Phone, ProvinceCode, CityCode, CountyCode)
                .whereBuilder()
                .andEq(AssistCalculateSummaryId, assistCalculateSummaryId)
                .andEq(DelFlag, false)
                .andEq(TenantId, tokenService.getThreadLocalTenantId());
        AssistCalculateCustomer assistCalculateCustomer = mapper.get(wrapper);
        GetAssistCalculateCustomerVo result = objectConvertor.toGetAssistCalculateCustomerVo(assistCalculateCustomer);
        return result;
    }
}
