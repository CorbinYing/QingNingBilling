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
package com.xiesu.common.handler;

import com.xiesu.common.except.AbstractCustomerException;
import com.xiesu.common.except.ServiceException;
import com.xiesu.common.response.ErrResponseResult;
import com.xiesu.common.response.ResponseBuildUtil;
import com.xiesu.common.response.ResponseResult;
import java.util.MissingResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Action: 全局处理异常 该类处理之后依旧会被
 * {@link ResponseHandlerAdvice#beforeBodyWrite(Object, MethodParameter, MediaType, Class,
 * ServerHttpRequest, ServerHttpResponse)} 拦截处理
 *
 * @author xiesu
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * 全局处理手动定义的异常 并进行封装
     */
    @ExceptionHandler(value = {ServiceException.class})
    public ResponseResult handleServiceException(AbstractCustomerException e) {
        log.error(e.getClass().getName(), e);
        return ResponseBuildUtil.failed(e);
    }

    @ExceptionHandler(value = {MissingResourceException.class})
    public ErrResponseResult handleMissingResourceException(MissingResourceException e) {
        log.error("国际化配置文件无对应errCode", e);
        return ResponseBuildUtil.failed().code(-1).errMsg("国际化配置文件无对应errCode").build();
    }


    /**
     * 处理其他非自定义运行时异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ErrResponseResult handleRuntimeException(RuntimeException e) {
        log.error(e.getClass().getName(), e);
        return ResponseBuildUtil.failed().code(-1).errMsg("其他异常").build();
    }


}
