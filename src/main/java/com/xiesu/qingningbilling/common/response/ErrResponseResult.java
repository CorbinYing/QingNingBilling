package com.xiesu.qingningbilling.common.response;

import com.xiesu.qingningbilling.common.except.AbstractCustomerException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;

/**
 * Action：程序异常统一返回结果
 *
 * @author xiesu
 */
@Getter
public class ErrResponseResult extends AbstractResponse {

    private ErrResponseResult(AbstractCustomerException e) {

        Assert.isTrue(Objects.nonNull(e.getCode()), "返回值必须包含err_code");

        Integer code = e.getCode();
        String msg = e.getMsg();
        Object[] params = e.getParams();

        //消息msg则进行国际化处理
        //获取当前线程的local，获取不到使用defaultLocale
        Locale locale = LocaleContextHolder.getLocale();
        if (StringUtils.isNoneBlank(msg)) {
            msg = params == null ? msg : MessageFormat.format(msg, params);
        } else {
            msg = ResponseDefaultMsg.getDefaultMsg(code, locale, params);
        }

        responseMap.put(ERR_CODE_KEY, code);
        responseMap.put(ERR_MSG_KEY, msg);
    }

    /**
     * 构造异常返回结果
     *
     * @param e e
     */
    public static ErrResponseResult failed(AbstractCustomerException e) {
        return new ErrResponseResult(e);
    }


}
