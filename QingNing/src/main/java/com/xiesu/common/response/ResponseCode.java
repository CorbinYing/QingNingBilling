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
package com.xiesu.common.response;

import java.io.Serializable;

/**
 * 定义定义错误码，与配置文件的code相对应
 */
public class ResponseCode implements Serializable {


    /**
     * 成功
     */
    public static final Integer SUCC_0 = 0;


    /**
     * 系统内部异常
     */
    public static final Integer ERR_CONF = 100001;


    public static final Integer ERR_101000 = 101000;

}
