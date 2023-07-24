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
package com.xiesu.controller.account;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.xiesu.common.response.OkResponseResult;
import com.xiesu.common.response.ResponseBuildUtil;
import com.xiesu.controller.AbstractBaseController;
import com.xiesu.security.ES256kJwtUtil;
import com.xiesu.security.JwtClaimBuilder;
import java.util.Date;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理登陆相关业务接口层
 *
 * @author xiesu
 */
@RestController
@RequestMapping("/auth")
public class UserAuthenticationController extends AbstractBaseController {


    /**
     * 账户、密码登陆
     *
     * @param authId        账号
     * @param pwdCiphertext 密码密文
     */
    @PostMapping("/pwd-auth")
    public OkResponseResult<Map<String, Object>> loginByPwd(@RequestParam("auth-id") String authId,
            @RequestParam("pwd-ciphertext") String pwdCiphertext)
            throws JOSEException {

        AuthenticationToken token = new UsernamePasswordToken(authId, pwdCiphertext);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        JWTClaimsSet accessClaims = JwtClaimBuilder.builder()
                .claim("name", "test")
                .audience(authId)
                .claim("type", "accessToken")
                .build();

        JWTClaimsSet refreshClaims = JwtClaimBuilder.builder()
                .audience(authId)
                .expirationTime(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .claim("type", "refreshToken")
                .build();

        String accessToken = ES256kJwtUtil.encode(accessClaims);
        String refreshToken = ES256kJwtUtil.encode(refreshClaims);

        return ResponseBuildUtil.success()
                .item("accessToken", accessToken)
                .item("tokenType", "Bearer")
                .item("refreshToken", refreshToken)
                .build();

    }
}
