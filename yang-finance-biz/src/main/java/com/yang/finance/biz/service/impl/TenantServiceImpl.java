package com.yang.finance.biz.service.impl;

import com.yang.common.exception.BizException;
import com.yang.finance.biz.domain.Tenant;
import com.yang.finance.biz.mapper.TenantMapper;
import com.yang.finance.biz.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {
    final TenantMapper tenantMapper;

    /**
     * 创建租户
     *
     * @return
     */
    @Override
    public long add() {
        Tenant tenant = new Tenant();
        tenant.initDefault();
        if (tenantMapper.insert(tenant) == 0) {
            throw new BizException("创建租户失败");
        }
        return tenant.getId();
    }
}
