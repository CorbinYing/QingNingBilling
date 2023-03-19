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

import com.xiesu.qingningbilling.common.except.AbstractCustomerException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author xiesu
 */
public interface AbstractResponse extends Serializable {

    /**
     * 返回内容的code值，code=0成功，其他均为时报
     */
    String ERR_CODE_KEY = "err_code";
    /**
     * 返回消息
     */
    String ERR_MSG_KEY = "err_msg";
    /**
     * 返回结果
     */
    String RES_RESULT_KEY = "result";
    /**
     * 返回结果只有一个字段内容时，默认该返回值的key=data
     */
    String SINGLE_RESULT_KEY = "data";

    /**
     * {@link OkResponseResult#success()}或{@link
     * ErrResponseResult#failed(AbstractCustomerException)} ()}builder的构建结果，包含code，msg
     */
    Map<Object, Object> responseMap = new LinkedHashMap<>();


    default Map<?, ?> getResult() {
        return responseMap;
    }

}
