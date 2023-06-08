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
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;

/**
 * Action：程序异常统一返回结果，只返回err_code和err_msg
 *
 * @author xiesu
 */
@Getter
public class ErrResponseResult extends ResponseResult {

    /**
     * 返回错误消息
     */
    private String errMsg;


    /**
     * 构造返回结果，自定义msg优先级高于国际化默认msg
     *
     * @param e {@link AbstractCustomerException}
     */
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

        this.setCode(code);
        this.errMsg = msg;
    }

    private ErrResponseResult(int code, String msg) {

        //消息msg则进行国际化处理
        //获取当前线程的local，获取不到使用defaultLocale
        Locale locale = LocaleContextHolder.getLocale();
        if (StringUtils.isBlank(msg)) {
            msg = ResponseDefaultMsg.getDefaultMsg(code, locale);
        }

        this.setCode(code);
        this.errMsg = msg;
    }


    /**
     * 构造异常返回结果
     *
     * @param e e
     */
    static ErrResponseResult failed(AbstractCustomerException e) {
        return new ErrResponseResult(e);
    }


    static ErrResponseResult failed(int code, String msg) {
        return new ErrResponseResult(code, msg);
    }
}
