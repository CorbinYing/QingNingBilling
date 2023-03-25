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
package com.xiesu.common.except;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * 可向调用者公开的异常信息 具体拦截
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
