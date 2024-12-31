package com.yang.common.service.impl;

import com.yang.common.config.SecurityConfig;
import com.yang.common.constant.CommonConstant;
import com.yang.common.dto.BaseUserInfoDTO;
import com.yang.common.exception.BizException;
import com.yang.common.exception.LoginException;
import com.yang.common.service.AuthFilterService;
import com.yang.common.service.TokenService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ConditionalOnProperty(prefix = "sys", name = "enable-my-security", havingValue = "true")
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFilterServiceImpl<T> implements AuthFilterService<T> {
    final TokenService<T> tokenService;
    final AntPathMatcher antPathMatcher;
    final SecurityConfig securityConfig;//通过的白名单
    final ObjectMapper jsonMapper;
    final HandlerExceptionResolver handlerExceptionResolver;//拦截异常捕获错误
    final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * 过滤器
         * 接口到达控制器前 会先经过 过滤器
         */

        try {
            //配置为空 或 没有配置 不进行拦截, 交给下一个过滤器
            if (securityConfig == null || !securityConfig.getEnable()) {
                filterChain.doFilter(request, response);
                return;
            }
            //取得类型是token 非网关(gateway)的话 去确认token 刷新
            T userInfo = null;
            if ("token".equals(securityConfig.getGetUserType())) {
                String token = request.getHeader("api-access-token");
                userInfo = tokenService.checkToken(token);
            }
            //
            if ("gateway".equals(securityConfig.getGetUserType())) {
                String userInfoJson = request.getHeader("user");
                userInfo = jsonMapper.readValue(userInfoJson, new TypeReference<T>() {
                });
            }
            if (userInfo == null) {
                throw new LoginException("无法获取到用户信息");
            }
            //转换为各种用户ID 还有token信息
            BaseUserInfoDTO baseUserInfoDTO = (BaseUserInfoDTO) userInfo;
            //检查权限
            checkPermissions(baseUserInfoDTO.getSysRoleIds(), request.getServletPath());
            //T userInfo = userService.getRedisUser(tokenResponse.getToken());
            // 用户信息存储在线程中
            tokenService.setThreadLocalUser(userInfo);//把信息存入setThreadLocalUser方法中,方便之后在其他地方使用 不再重复调用redis
            //把 请求参数,返回结果 传给下一个过滤器
            filterChain.doFilter(request, response);
            //关键 把信息删除,防止资料泄露
            tokenService.removeThreadLocalUser();

            //统一的全局异常返回
        } catch (Exception ex) {
            handlerExceptionResolver.resolveException(request, response,
                    null, ex);
        }
    }

    /**
     * 不经过过滤器筛选 白名单
     *
     * @param request
     * @return
     * @throws ServletException
     */
    @Override
    public boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        //没拿到信息 等错误
        if (securityConfig == null || !securityConfig.getEnable() || CollectionUtils.isEmpty(securityConfig.getIgnores())) {
            return false;
        }
        String path = request.getServletPath();
        //通过这个类的方法 匹配白名单
        boolean ignore = securityConfig.getIgnores().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
        if (log.isDebugEnabled()) {
            log.info("path: {} [ignore: {}]", path, ignore);
        }
        return ignore;
    }

    /**
     * 检查权限
     */
    public void checkPermissions(Set<Long> sysRoleIds, String path) {
        //如果是管理员则不检查权限，拥有所有权限
        if (sysRoleIds.contains(CommonConstant.ROLE_ADMIN)) {
            return;
        }
        //从缓存中获取角色对应的菜单id
        Set<String> roleBindResourcePaths = listRoleResourcePathByCache(sysRoleIds);
         //如果缓存中取出的信息为空 抛出异常
        if (CollectionUtils.isEmpty(roleBindResourcePaths)) {
            throw new BizException("角色没有绑定任何资源");
        }
        //遍历缓存中菜单 把遍历出的菜单和请求路径做匹配
        for (String url : roleBindResourcePaths) {
            if (antPathMatcher.match(url, path)) {
                return;
            }
        }
        throw new BizException("非法访问");
    }

    /**
     * 从缓存中获取角色对应的菜单id
     *
     * 用hash从redis中取脚色ID值 这里为集合
     * 转化直收集起来
     * 把多个集合融为一个集合
     *
     * @param roleIds
     * @return
     */
    private Set<String> listRoleResourcePathByCache(Set<Long> roleIds) {
        //获取一个操作Redis哈希数据结构的操作对象<key,字段（field）,对应的值（value）>
        HashOperations<String, String, Set<String>> hashOps = redisTemplate.opsForHash();
        //把hashOps 聚合起来("ROLE_RESOURCE_PERMISSIONS:",stream()聚合roleIds .map(String::valueOf)转化stream中每一个元素把它变成String类型.终端指令(用收集器Collectors.toSet()把stream中所有元素存到一起,.toSet()特性不会重复))
        List<Set<String>> roleMenuIds = hashOps.multiGet(CommonConstant.ROLE_RESOURCE_PERMISSIONS, roleIds.stream().map(String::valueOf).collect(Collectors.toSet()));
        // 对结果进行处理，将 List<List<String>> 转为 List<String>  !把多个集合 融为 一个集合
        return roleMenuIds.stream()
                .filter(p -> !CollectionUtils.isEmpty(p))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
