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
    Map<Object,Object> responseMap = new LinkedHashMap<>();


    default Map<?, ?> getResult() {
        return responseMap;
    }

}
