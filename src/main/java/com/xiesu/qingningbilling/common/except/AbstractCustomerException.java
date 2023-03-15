package com.xiesu.qingningbilling.common.except;

import java.util.Objects;
import lombok.Getter;

/**
 * 自定义异常基类
 */
@Getter
public abstract class AbstractCustomerException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 自定义消息
     */
    private final String msg;

    /**
     * 对自定义消息或默认消息进行格式化的参数，具体格式化方式{@link java.text.MessageFormat}
     */
    private final Object[] params;


    protected AbstractCustomerException(Integer code, String msg, Object[] params) {
        this.code = Objects.requireNonNull(code);
        this.msg = msg;
        this.params = params;
    }
}
