package com.xiesu.qingningbilling.common.response;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.springframework.context.i18n.LocaleContextHolder;


/**
 * Action: 正常情况下构建统一返回结果
 *
 * 返回格式
 * {
 *    err_code:xxx,
 *    err_msg:xxx,
 *    result:{
 *        xxx:{
 *            xxx:xxx,
 *            xxx:xxx
 *        },
 *        xxx:xxx
 *    }
 * }
 *
 * @author xiesu
 */
public class OkResponseResult extends AbstractResponse {

    private OkResponseResult(Map<Object, Object> data) {
        responseMap.put(ERR_CODE_KEY, ResponseCode.SUCC_0);

        //获取当前线程的local，获取不到使用defaultLocale
        Locale locale = LocaleContextHolder.getLocale();
        responseMap.put(ERR_MSG_KEY, ResponseDefaultMsg.getDefaultMsg(ResponseCode.SUCC_0, locale));
        responseMap.put(RES_RESULT_KEY, data);
    }


    public static SucResponseBuilder success() {
        return new SucResponseBuilder();
    }

    public static class SucResponseBuilder {

        /**
         * 存放最终结果的有序mapß
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
        public <K,V> SucResponseBuilder items(Map<K, V> map) {
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
