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

import com.xiesu.common.except.AbstractCustomerException;
import com.xiesu.utils.ObjectsUtil;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 返回结果抽象接口
 *
 * @author xiesu
 */
public abstract class AbstractResponse implements Serializable {

    /**
     * 返回内容的code值，code=0成功，其他均为时报
     */
    protected String ERR_CODE_KEY = "err_code";
    /**
     * 返回消息
     */
    protected String ERR_MSG_KEY = "err_msg";
    /**
     * 返回结果
     */
    protected String RES_RESULT_KEY = "result";
    /**
     * {@link OkResponseResult#success()}或{@link
     * ErrResponseResult#failed(AbstractCustomerException)} ()}builder的构建结果，包含code，msg
     */
    protected Map<Object, Object> responseMap;


    /**
     * 返回一个不允许修改的只读map，不允许直接操作封装map
     */
    public final Map<?, ?> getImmutableResult() {
        return Map.copyOf(ObjectsUtil.orElse(responseMap, Collections.EMPTY_MAP));
    }


    /**
     * 初始化responseMap方法
     */
    protected AbstractResponse() {
        responseMap = new LinkedHashMap<>();
    }

}
