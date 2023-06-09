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

import com.xiesu.controller.AbstractBaseController;
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
@RequestMapping("/login")
public class UserLoginBaseController extends AbstractBaseController {


    /**
     * 账户、密码登陆
     *
     * @param authId        账号
     * @param pwdCiphertext 密码密文
     */
    @PostMapping("/pwd-auth")
    public boolean loginByPwd(@RequestParam("auth-id") String authId,
            @RequestParam("pwd-ciphertext") String pwdCiphertext) {

        AuthenticationToken token = new UsernamePasswordToken(authId, pwdCiphertext);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        System.out.println(subject);

        return true;

    }
}
