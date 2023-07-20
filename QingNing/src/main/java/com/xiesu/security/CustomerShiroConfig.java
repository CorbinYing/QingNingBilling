/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xiesu.security;


import jakarta.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro 核心配置
 *
 * @author xiesu created on 2023/4/20 14:31
 */
@Configuration
public class CustomerShiroConfig {

    /**
     * 配置securityManager
     */
    @Bean
    DefaultWebSecurityManager defaultWebSecurityManager(AuthenticatingRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        realm.setCredentialsMatcher(new CustomerCredentialsMatcher());
        securityManager.setRealm(realm);

        return securityManager;

    }


    /**
     * 设置访问拦截器
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        //指定除了login和logout之外的请求都先经过jwtFilter
        Map<String, Filter> filterMap = new HashMap<>();
        //这个地方其实另外两个filter可以不设置，默认就是
        //将jwtFilter注册到shiro的Filter中
        filterMap.put("jwt", new CustomerShiroFilter());
        //shiro有几种默认的拦截器，authc,anno,roles,user等 authc就是FormAuthenticationFilter的实例
        filterMap.put("anon", new AnonymousFilter());
        filterMap.put("logout", new LogoutFilter());

        //配置资源访问权限,注意使用【有序MAP】，【/** 放最后】，否则范围大的现匹配，导致多个过滤器工作不正常
        Map<String, String> ruleMap = new LinkedHashMap<>();
        ruleMap.put("/login/pwd-auth", "anon");
        //设置其他所有请求 使用自定义过滤器，未登录不允许进入。
        //一条路径配俩filter的语法，逗号间隔(还是翻源码看出来的，官方文档都没有)
        ruleMap.put("/**", "jwt");

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //配置安全管理器
        shiroFilter.setSecurityManager(securityManager);
        //对应NoSessionCreationFilter，全局无状态,以及默认的invalidRequest 过滤器
        shiroFilter.setGlobalFilters(List.of(DefaultFilter.noSessionCreation.name(),
                DefaultFilter.invalidRequest.name()));
        //配置过滤器
        shiroFilter.setFilters(filterMap);
        //配置过滤规则
        shiroFilter.setFilterChainDefinitionMap(ruleMap);

        return shiroFilter;
    }

}
