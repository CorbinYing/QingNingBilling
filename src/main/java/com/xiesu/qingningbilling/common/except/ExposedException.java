package com.xiesu.qingningbilling.common.except;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * 可向调用者公开的异常信息
 * 具体拦截
 *
 * @author xiesu
 */
//@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ExposedException extends AbstractCustomerException {


    /**
     * Constructs a new runtime exception with the specified detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public ExposedException(Integer code) {
        super(code, null, null);
    }

    /**
     * Constructs a new runtime exception with the specified detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public ExposedException(Integer code, String msg) {
        super(code, msg, null);
    }


    /**
     * 使用传入的参数对默认消息进行格式化,具体格式化方式{@link MessageFormat}
     *
     * @param code   code notnull
     * @param params nullable
     */
    public ExposedException(Integer code, Object... params) {
        super(code, null, params);
    }

    /**
     * 根据传入的参数进行msg 进行格式化,具体格式化方式{@link MessageFormat}
     *
     * @param code   code
     * @param msg    not null
     * @param params 参数
     */
    public ExposedException(Integer code, String msg, Object... params) {
        super(code, MessageFormat.format(Objects.requireNonNull(msg), params), null);
    }

}
