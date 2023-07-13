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
import java.util.Map;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro 核心配置
 *
 * @author xiesu created on 2023/4/20 14:31
 */
@Configuration
public class ShiroConfig {

    @Bean
    DefaultWebSecurityManager defaultWebSecurityManager(AuthenticatingRealm realm,
            SubjectFactory subjectFactory) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        realm.setCredentialsMatcher(new CustomerCredentialsMatcher());
        securityManager.setRealm(realm);

        // 关闭 ShiroDAO 功能
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 不需要将 Shiro Session 中的东西存到任何地方（包括 Http Session 中）
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        //禁止Subject的getSession方法,使用自定义Factory
        securityManager.setSubjectFactory(subjectFactory);
        return securityManager;

    }

    /**
     * 设置访问拦截器
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //配置安全管理器
        shiroFilter.setSecurityManager(securityManager);
        ////配置未登录用户访问登陆用户的权限所跳转的页面
        //shiroFilter.setLoginUrl("/login/pwd-auth");
        ////设置成功后返回页面
        //shiroFilter.setSuccessUrl("/index");
        ////访问未授权网页所跳转的页面
        //shiroFilter.setUnauthorizedUrl("/unauthorized");

        //指定除了login和logout之外的请求都先经过jwtFilter
        Map<String, Filter> filterMap = new HashMap<>();
        //这个地方其实另外两个filter可以不设置，默认就是
        //将jwtFilter注册到shiro的Filter中
        filterMap.put("jwt", new JwtFilter());
        //shiro有几种默认的拦截器，authc,anno,roles,user等 authc就是FormAuthenticationFilter的实例
        filterMap.put("anon", new AnonymousFilter());
        filterMap.put("logout", new LogoutFilter());
        shiroFilter.setFilters(filterMap);

        //配置资源访问权限,注意使用【有序MAP】，【/** 放最后】，否则范围大的现匹配，导致多个过滤器工作不正常
        Map<String, String> map = new LinkedHashMap<>();
        //允许  需要设置login为anon 否则登陆成功后无法成功跳转。
        map.put("/login/pwd-auth", "anon");
        //设置其他所有请求 使用自定义过滤器，未登录不允许进入。
        map.put("/**", "jwt");

        shiroFilter.setFilterChainDefinitionMap(map);

        return shiroFilter;
    }

}
