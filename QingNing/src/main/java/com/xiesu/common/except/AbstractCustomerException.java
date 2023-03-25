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

import java.util.Objects;
import lombok.Getter;

/**
 * 自定义异常基类
 *
 * @author xiesu
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
