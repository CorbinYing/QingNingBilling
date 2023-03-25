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
package com.xiesu.controller;

/**
 * 公共Controller，所有Controller均须继承，可定义通用相关方法
 *
 * @author xiesu
 */
public abstract class AbstractBaseController {

    /**
     * 获取当前登陆人账户
     *
     * @return 账户id
     */
    public String getCurrentUser() {
        return "的富家女岁";
    }

}
