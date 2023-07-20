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
import java.text.ParseException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义密码匹配
 *
 * @author xiesu created on 2023/4/20 14:19
 */
public class CustomerCredentialsMatcher extends SimpleCredentialsMatcher {

    private static final Logger logger = LoggerFactory.getLogger(CustomerCredentialsMatcher.class);

    /**
     * This implementation acquires the {@code token}'s credentials (via
     * {@link #getCredentials(AuthenticationToken) getCredentials(token)}) and then the
     * {@code account}'s credentials (via
     * {@link #getCredentials(AuthenticationInfo) getCredentials(account)}) and then passes both of
     * them to the {@link #equals(Object, Object) equals(tokenCredentials, accountCredentials)}
     * method for equality comparison.
     *
     * @param token the {@code AuthenticationToken} submitted during the authentication attempt.
     * @param info  the {@code AuthenticationInfo} stored in the system matching the token
     *              principal.
     * @return {@code true} if the provided token credentials are equal to the stored account
     * credentials, {@code false} otherwise
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (token instanceof JwtToken) {
            return doCredentialsMatchJwtToken((JwtToken) token);
        } else {
            return super.doCredentialsMatch(token, info);
        }
    }

    /**
     * j校验token
     *
     * @param token jwtToken
     * @return boolean
     */
    private boolean doCredentialsMatchJwtToken(JwtToken token) {
        try {
            return ES256kJwtUtil.signatureVerify((String) token.getCredentials());
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * JWT Token 认证
     * <p></>
     * Returns {@code true} if the {@code tokenCredentials} argument is logically equal to the
     * {@code accountCredentials} argument.
     * <p/>
     * <p>If both arguments are either a byte array (byte[]), char array (char[]) or String, they
     * will be both be converted to raw byte arrays via the {@link #toBytes toBytes} method first,
     * and then resulting byte arrays are compared via
     * {@link Arrays#equals(byte[], byte[]) Arrays.equals(byte[],byte[])}.</p>
     * <p/>
     * <p>If either argument cannot be converted to a byte array as described, a simple Object
     * <code>equals</code>
     * comparison is made.</p>
     * <p/>
     * <p>Subclasses should override this method for more explicit equality checks.
     *
     * @param tokenCredentials   the {@code AuthenticationToken}'s associated credentials.
     * @param accountCredentials the {@code AuthenticationInfo}'s stored credentials.
     * @return {@code true} if the {@code tokenCredentials} are equal to the
     * {@code accountCredentials}.
     */
    @Override
    protected boolean equals(Object tokenCredentials, Object accountCredentials) {
        return super.equals(tokenCredentials, accountCredentials);
    }
}
