package com.xiesu.qingningbilling.common.except;

import com.xiesu.qingningbilling.common.response.ResponseCode;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * Action ： 不可向外部暴露的异常信息，调用者只需知道系统异常即可， 一般业务不会手动抛出该类型异常，自写工具类转换等异常问题可选择该异常类型
 *
 * @author xiesu
 */

public class UnExposedException extends AbstractCustomerException {

    /**
     * 不可暴露异常，异常码为-1，不允许修改
     */
    public UnExposedException() {
        super(ResponseCode.ERR_SYSTEM, null, null);
    }

    /**
     * 不可暴露异常，异常码为-1，不允许修改
     */
    public UnExposedException(String msg) {
        super(ResponseCode.ERR_SYSTEM, msg, null);
    }


    /**
     * 不可暴露异常，异常码为-1，不允许修改 使用参数对传入的msg进行格式化
     *
     * @param errMsg not null
     * @param params null able
     */
    public UnExposedException(String errMsg, Object[] params) {
        super(ResponseCode.ERR_SYSTEM, MessageFormat.format(Objects.requireNonNull(errMsg), params),
                null);
    }

    /**
     * 使用此方法时，将会在异常捕获时使用参数对「默认」消息进行格式化,具体格式化方式{@link MessageFormat}
     *
     * @param params null able
     */
    public UnExposedException(Object... params) {
        super(ResponseCode.ERR_SYSTEM, null, params);
    }

}
