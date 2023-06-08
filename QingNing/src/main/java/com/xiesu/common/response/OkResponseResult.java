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


/**
 * Action: 正常情况下构建统一返回结果
 * <p>
 * 返回格式 { err_code:xxx, err_msg:xxx, result:{ xxx:{ xxx:xxx, xxx:xxx }, xxx:xxx } }
 *
 * @author xiesu
 */
public class OkResponseResult<T> extends ResponseResult {

    /**
     * 返回结果
     */
    private final T result;

    private OkResponseResult(T singleResponseResult) {
        this.setCode(ResponseCode.SUCC_0);
        this.result = singleResponseResult;
    }

    static <T> OkResponseResult<T> success(T singleResponseResult) {
        return new OkResponseResult<>(singleResponseResult);
    }

    public T getResult() {
        return result;
    }
}
