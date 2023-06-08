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
 * 抽象统一返回结果封装类
 *
 * @author xiesu
 */
public abstract class ResponseResult implements Serializable {


    /**
     * 返回内容的code值，code=0成功，其他均为失败
     */
    private Integer code;


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


}
