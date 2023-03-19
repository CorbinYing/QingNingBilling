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

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.springframework.context.i18n.LocaleContextHolder;


/**
 * Action: 正常情况下构建统一返回结果
 * <p>
 * 返回格式 { err_code:xxx, err_msg:xxx, result:{ xxx:{ xxx:xxx, xxx:xxx }, xxx:xxx } }
 *
 * @author xiesu
 */
public class OkResponseResult implements AbstractResponse {

    private OkResponseResult(Map<Object, Object> data) {
        responseMap.put(ERR_CODE_KEY, ResponseCode.SUCC_0);

        //获取当前线程的local，获取不到使用defaultLocale
        Locale locale = LocaleContextHolder.getLocale();
        responseMap.put(ERR_MSG_KEY, ResponseDefaultMsg.getDefaultMsg(ResponseCode.SUCC_0, locale));
        responseMap.put(RES_RESULT_KEY, data);
    }

    private OkResponseResult(Object singleResponseResult) {
        responseMap.put(ERR_CODE_KEY, ResponseCode.SUCC_0);

        //获取当前线程的local，获取不到使用defaultLocale
        Locale locale = LocaleContextHolder.getLocale();
        responseMap.put(ERR_MSG_KEY, ResponseDefaultMsg.getDefaultMsg(ResponseCode.SUCC_0, locale));
        responseMap.put(RES_RESULT_KEY, singleResponseResult);
    }

    public static OkResponseResult success(Object singleResponseResult) {
        return new OkResponseResult(singleResponseResult);
    }

    /**
     * 链式构造返回结果
     *
     * @return SucResponseBuilder
     */
    public static SucResponseBuilder success() {
        return new SucResponseBuilder();
    }

    public static class SucResponseBuilder {

        /**
         * 存放最终结果的有序
         */
        private final Map<Object, Object> data = new LinkedHashMap<>();

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
        public <K, V> SucResponseBuilder items(Map<K, V> map) {
            if (!(map == null || map.isEmpty())) {
                data.putAll(map);
            }
            return this;
        }

        /**
         * 构建外部结果类
         */
        public OkResponseResult build() {
            return new OkResponseResult(data);
        }
    }
}
