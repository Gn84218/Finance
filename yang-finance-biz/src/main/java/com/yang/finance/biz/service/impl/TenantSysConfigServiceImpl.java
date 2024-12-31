package com.yang.finance.biz.service.impl;

import com.yang.common.exception.BizException;
import com.yang.common.service.TokenService;
import com.yang.finance.biz.config.ObjectConvertor;
import com.yang.finance.biz.constant.TenantSysConfigKeyConstant;
import com.yang.finance.biz.domain.TenantSysConfig;
import com.yang.finance.biz.dto.AdminDTO;
import com.yang.finance.biz.dto.vo.SubjectCodeLengthConfigVo;
import com.yang.finance.biz.mapper.TenantSysConfigMapper;
import com.yang.finance.biz.service.TenantSysConfigService;
import com.yang.mybatis.help.MyBatisWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.yang.finance.biz.domain.TenantSysConfigField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TenantSysConfigServiceImpl implements TenantSysConfigService {
    final TenantSysConfigMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;
    final ObjectMapper jsonMapper;
    final RedisTemplate<String, String> redisTemplate;

    @Override
    public SubjectCodeLengthConfigVo getSubjectCodeLengthConfig() {
        MyBatisWrapper<TenantSysConfig> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, ConfigValue)
                .whereBuilder()
                .andEq(setTenantId(tokenService.getThreadLocalTenantId()))
                .andEq(setConfigKey(TenantSysConfigKeyConstant.SUBJECT_CODE_LENGTH_CONFIG))
                .andEq(setDelFlag(false));
        TenantSysConfig tenantSysConfig = mapper.get(wrapper);
        if (tenantSysConfig == null) {
            throw new BizException("科目编码长度配置为空");
        }
        try {
            return jsonMapper.readValue(tenantSysConfig.getConfigValue(), SubjectCodeLengthConfigVo.class);
        } catch (Exception ex) {
            throw new BizException("解析科目编码长度配置异常");
        }
    }
}