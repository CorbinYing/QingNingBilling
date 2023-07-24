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
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiesu created on 2023/6/2 17:07
 */
public class ResponseBuildUtil implements Serializable {

    private ResponseBuildUtil() {
    }

    public static <T> OkResponseResult<T> success(T result) {
        return OkResponseResult.success(result);
    }

    /**
     * 链式构造返回结果
     *
     * @return SucResponseBuilder
     */
    public static SucResponseBuilder success() {
        return new SucResponseBuilder();
    }


    public static ErrResponseBuilder failed() {
        return new ErrResponseBuilder();
    }


    public static ErrResponseResult failed(AbstractCustomerException e) {
        return ErrResponseResult.failed(e);
    }


    public static class SucResponseBuilder {

        private SucResponseBuilder() {
        }

        /**
         * 存放最终结果的有序
         */
        private final Map<String, Object> data = new LinkedHashMap<>();

        /**
         * 具体的结果值
         *
         * @param key   notnull
         * @param value nullable
         */
        public SucResponseBuilder item(String key, Object value) {
            data.put(Objects.requireNonNull(key), value);
            return this;
        }

        /**
         * 具体的结果值
         *
         * @param map notnull
         */
        public <V> SucResponseBuilder items(Map<String, V> map) {
            if (!(map == null || map.isEmpty())) {
                data.putAll(map);
            }
            return this;
        }

        public OkResponseResult<Map<String, Object>> build() {
            return OkResponseResult.success(data);
        }
    }


    public static class ErrResponseBuilder {

        private Integer code;
        private String msg;

        private ErrResponseBuilder() {
        }


        public ErrResponseBuilder code(int code) {
            this.code = code;
            return this;
        }

        public ErrResponseBuilder errMsg(String errMsg) {
            this.msg = errMsg;
            return this;
        }

        /**
         * 构建外部结果类
         */
        public ErrResponseResult build() {
            return ErrResponseResult.failed(code, msg);
        }
    }


}
