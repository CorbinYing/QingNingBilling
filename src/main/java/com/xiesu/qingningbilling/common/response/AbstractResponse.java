package com.xiesu.qingningbilling.common.response;

import com.xiesu.qingningbilling.common.except.AbstractCustomerException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author xiesu
 */
public abstract class AbstractResponse implements Serializable {

    protected static String ERR_CODE_KEY = "err_code";
    protected static String ERR_MSG_KEY = "err_msg";
    protected static String RES_RESULT_KEY = "result";

    /**
     * {@link OkResponseResult#success()}或{@link
     * ErrResponseResult#failed(AbstractCustomerException)} ()}builder的构建结果，包含code，msg
     */
    protected final Map<Object, Object> responseMap = new LinkedHashMap<>();


    public Map<Object, Object> getResult() {
        return responseMap;
    }

}
