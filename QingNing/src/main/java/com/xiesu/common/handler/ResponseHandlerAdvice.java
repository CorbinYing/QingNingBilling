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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiesu.common.response.OkResponseResult;
import com.xiesu.common.response.ResponseBuildUtil;
import com.xiesu.common.response.ResponseResult;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 拦截Spring返回结果，包装返回值，统一返回格式
 *
 * @author xiesu
 */
@Slf4j
@RestControllerAdvice
public class ResponseHandlerAdvice implements ResponseBodyAdvice<Object> {

    /**
     * Whether this component supports the given controller method return type and the selected
     * {@code HttpMessageConverter} type.
     *
     * @param returnType    the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked; {@code false} otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {

        //System.out.println(returnType);
        //System.out.println(returnType.getMethod());
        //System.out.println(returnType.getMethod().getGenericReturnType());
        ////System.out.println(returnType.getMethod().getExceptionTypes());
        //System.out.println(returnType.getMethod().getReturnType());

        return true;
        //return false;
    }

    /**
     * 如果 请求头 包含Accept-Language 参数，则根据该参数进行国际化处理；<p> 参数格式  Accept-Language ：zh-CN
     *
     *
     * <p>
     * Invoked after an {@code HttpMessageConverter} is selected and just before its write method is
     * invoked.
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
            MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {

        //格式化返回结果
        body = formatBody(body, returnType, response);
        //TODO :加密body
        return body;


    }

    /**
     * 对返回结果进行格式化封装
     *
     * @param body object
     * @return {@link ResponseResult}
     */
    private Object formatBody(Object body, MethodParameter returnType,
            ServerHttpResponse response) {
        if (body instanceof ResponseResult) {
            return body;
        } else if (body instanceof String) {
            ((ServletServerHttpResponse) response).getServletResponse()
                    .setContentType(MediaType.APPLICATION_JSON_VALUE);
            //String 是直接返回，需要手动转json，不然会报错
            try {
                return new ObjectMapper().writeValueAsString(
                        ResponseBuildUtil.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        //其他类型则直接构建
        return ResponseBuildUtil.success(body);
    }

}
