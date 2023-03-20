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
package com.xiesu.qingningbilling.common.response;

import java.io.Serializable;

/**
 * 定义定义错误码，与配置文件的code相对应
 */
public class ResponseCode implements Serializable {

    /**
     * 系统内部异常，一般不会手动调用
     */
    public static final Integer ERR_SYSTEM = -1;

    /**
     * 成功
     */
    public static final Integer SUCC_0 = 0;

    /**
     * 参数错误
     */
    public static final Integer ERR_10001 = 10001;

    /**
     * 邮箱格式错误
     */
    public static final Integer ERR_10002 = 10002;
    /**
     * 账号不存在
     */
    public static final Integer ERR_11001 = 11001;
    /**
     * 密码不正确
     */
    public static final Integer ERR_11002 = 11002;
    /**
     * 登录失效
     */
    public static final Integer ERR_11003 = 11003;


    /**
     * 验证码发送失败
     */
    public static final Integer ERR_12001 = 12001;

    /**
     * 验证码错误
     */
    public static final Integer ERR_12002 = 12002;
    /**
     * 验证码已失效
     */
    public static final Integer ERR_12003 = 12003;


    /**
     * 该邮箱已注册，请直接登录
     */
    public static final Integer ERR_13001 = 13001;
    /**
     *
     */
    public static final Integer ERR_11005 = 11005;
    /**
     *
     */
    public static final Integer ERR_11006 = 11006;
    /**
     *
     */
    public static final Integer ERR_11007 = 11007;
    /**
     *
     */
    public static final Integer ERR_11008 = 11008;
    /**
     *
     */
    public static final Integer ERR_11009 = 11009;


}
