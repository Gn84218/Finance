package com.yang.finance.biz.factory;

import com.yang.finance.biz.service.AssistCalculateHandleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 辅助核算 工厂模式
 * 根据枚举值的code(如客户)调用接口的类
 */

@Service
@RequiredArgsConstructor
public class AssistCalculateHandlerFactory implements InitializingBean {
    //注入辅助核算 CRUD接口
    final List<AssistCalculateHandleService> assistCalculateHandleServices;

    private Map<String, AssistCalculateHandleService> assistCalculateHandleServiceMap
            = new HashMap<>();

    /**
     * 获取辅助类别对应的辅助核算服务
     *
     * @param cateCode
     * @return
     */
    public AssistCalculateHandleService get(String cateCode) {
        return assistCalculateHandleServiceMap.get(cateCode);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        assistCalculateHandleServices.forEach(item -> {
            assistCalculateHandleServiceMap.put(item.getAssistCalculateCateCodeEnum().getCode(), item);
        });
    }
}
