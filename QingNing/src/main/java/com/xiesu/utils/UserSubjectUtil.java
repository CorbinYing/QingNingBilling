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
package com.xiesu.utils;

import com.xiesu.common.security.auth.Role;

/**
 * 用户信息获取工具类
 *
 * @author xiesu created on 2023/4/3 16:18
 */
public final class UserSubjectUtil {

    /**
     * 获取当前登陆用户信息
     */
    public static UserSubject currentUserSubject() {
        return new UserSubject("accountId", "mail", "tel", Role.USER);
    }


    public static class UserSubject {

        /**
         * 账户唯一标识id
         */
        private String accountId;
        /**
         * 绑定邮箱(nullable,但逻辑唯一)
         */
        private String mail;
        /**
         * 绑定手机号(nullable,但逻辑唯一)
         */
        private String tel;
        /**
         * 角色
         */
        private Role role;


        private UserSubject(String accountId, String mail, String tel, Role role) {
            this.accountId = accountId;
            this.mail = mail;
            this.tel = tel;
            this.role = role;
        }

        public String getAccountId() {
            return accountId;
        }

        public String getMail() {
            return mail;
        }

        public String getTel() {
            return tel;
        }

        public Role getRole() {
            return role;
        }
    }

}
