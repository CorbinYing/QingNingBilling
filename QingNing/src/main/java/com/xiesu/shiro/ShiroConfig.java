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
package com.xiesu.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiesu created on 2023/4/20 14:31
 */
@Configuration
public class ShiroConfig {

    @Bean
    DefaultWebSecurityManager defaultWebSecurityManager(AuthenticatingRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        realm.setCredentialsMatcher(new MyCredentialsMatcher());

        securityManager.setRealm(realm);
        //securityManager.set
        return securityManager;
    }


    //设置访问拦截器
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //传入安全管理器
        bean.setSecurityManager(securityManager);
        //传入未登录用户访问登陆用户的权限所跳转的页面
        bean.setLoginUrl("/login/pwd-auth");

        //设置成功后返回页面
        bean.setSuccessUrl("/index");

        //访问未授权网页所跳转的页面
        bean.setUnauthorizedUrl("/unauthorized");
        Map<String, String> map = new LinkedHashMap<>();
        //允许  需要设置login为anon 否则登陆成功后无法成功跳转。
        map.put("/login", "anon");
        map.put("/doLogin", "anon");
        map.put("/index", "anon");
        //设置所有的请求未登录不允许进入。
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

}
