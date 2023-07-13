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

import com.nimbusds.jose.JOSEException;
import com.xiesu.dao.UserAccountDao;
import jakarta.annotation.Resource;
import java.text.ParseException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro 将数据库中的数据，存放到Realm这种对象中,自定义认证与授权
 *
 * @author xiesu created on 2023/4/20 14:04
 */
@Configuration
public class CustomerJwtRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(CustomerJwtRealm.class);

    @Resource
    private UserAccountDao userAccountDao;


    /**
     * 多重写一个support 标识这个Realm是专门用来验证JwtToken 不负责验证其他的token（UsernamePasswordToken）
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }


    /**
     * 加载授权信息
     * <p/>
     * Retrieves the AuthorizationInfo for the given principals from the underlying data store. When
     * returning an instance from this method, you might want to consider using an instance of
     * {@link SimpleAuthorizationInfo SimpleAuthorizationInfo}, as it is suitable in most cases.
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be
     *                   retrieved.
     * @return the AuthorizationInfo associated with this principals.
     * @see SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

    /**
     * 认证
     * <p/>
     * Retrieves authentication data from an implementation-specific datasource (RDBMS, LDAP, etc)
     * for the given authentication token.
     * <p/>
     * For most datasources, this means just 'pulling' authentication data for an associated
     * subject/user and nothing more and letting Shiro do the rest.  But in some systems, this
     * method could actually perform EIS specific log-in logic in addition to just retrieving data -
     * it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified
     * token.
     *
     * @param token the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified
     *                                 <tt>token</tt>
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        String jwt = (String) token.getPrincipal();
        if (jwt == null) {
            throw new NullPointerException("jwtToken 不允许为空");
        }
        try {
            //签名有效即可认为认证成功
            if (!ES256kJwtUtil.signatureVerify(jwt)) {
                throw new UnknownAccountException();
            }

            //下面是验证这个user是否是真实存在的
            //判断数据库中username是否存在
            String username = (String) ES256kJwtUtil.decode(jwt).getClaim("username");
            logger.info("在使用token登录" + username);

        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
        //返回一个新封装的认证实体，传入的是身份信息，认证信息，和当前Realm的名字
        //shiro将账号和密码分成两个地方进行验证，如果我们不自己定义，那么就会调用shiro默认的校验方法。
        //此处 principal,   credentials 都是jwt
        return new SimpleAuthenticationInfo(jwt, jwt, this.getClass().getName());

    }

    //
    //@Override
    //protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
    //        throws AuthenticationException {
    //    String accountId = (String) token.getPrincipal();
    //    Objects.requireNonNull(accountId);
    //
    //    //查询密码
    //    String pwd = userAccountDao.selectPwdByAccountId(accountId);
    //    //检测不到用户名可以报错
    //    if (Objects.isNull(pwd)) {
    //        throw new ServiceException(ResponseCode.ERR_101000);
    //    }
    //
    //
    //    return new SimpleAuthenticationInfo(accountId, pwd, this.getName());
    //}
}
