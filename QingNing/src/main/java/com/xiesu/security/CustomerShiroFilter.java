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
package com.xiesu.security;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiesu.common.response.ErrResponseResult;
import com.xiesu.common.response.ResponseBuildUtil;
import com.xiesu.common.response.ResponseCode;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import org.apache.shiro.web.filter.authc.BearerHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * 自定义一个Filter，用来拦截所有的请求判断是否携带Token isAccessAllowed()判断是否携带了有效的JwtToken
 * onAccessDenied()是没有携带JwtToken的时候进行账号密码登录，登录成功允许访问，登录失败拒绝访问
 *
 * @author xiesu created on 2023/7/13 18:50
 */

public class CustomerShiroFilter extends BearerHttpAuthenticationFilter {
//public class JwtFilter extends BasicHttpAuthenticationFilter {
//public class JwtFilter extends AccessControlFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomerShiroFilter.class);


    /*
     * 1. 返回true，shiro就直接允许访问url
     * 2. 返回false，shiro才会根据onAccessDenied的方法的返回值决定是否允许访问url
     * */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
            Object mappedValue) {
        logger.warn("isAccessAllowed 方法被调用");
        //这里先让它始终返回false来使用onAccessDenied()方法
        return false;
    }

    ///**
    // * 返回结果为true表明登录通过
    // */
    //@Override
    //protected boolean onAccessDenied(ServletRequest servletRequest,
    //        ServletResponse servletResponse) throws Exception {
    //    logger.warn("onAccessDenied 方法被调用");
    //    //执行方法中没有抛出异常就表示登录成功
    //    return executeLogin(servletRequest, servletResponse);
    //}

    ///**
    // * 执行登陆
    // */
    //@Override
    //protected boolean executeLogin(ServletRequest request, ServletResponse response) {
    //    //这个地方和前端约定，要求前端将jwtToken放在请求的Header部分
    //    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    //    //所以以后发起请求的时候就需要在Header中放一个Authorization，值就是对应的Token
    //    String jwt = httpServletRequest.getHeader("Authorization");
    //    logger.info("Header中获取jwtToken={}", jwt);
    //    JwtToken jwtToken = new JwtToken(jwt);
    //
    //    // 委托 realm 进行登录认证
    //    //所以这个地方最终还是调用JwtRealm进行的认证
    //    //也就是subject.login(token)
    //    getSubject(request, response).login(jwtToken);
    //    // 没有异常即表示验证（登陆）状态成功
    //    return true;
    //}
    //


    /**
     * 认证失败的操作,401 状态码,返回错误信息
     * Builds the challenge for authorization by setting a HTTP <code>401</code> (Unauthorized)
     * status as well as the
     * response's {@link #AUTHENTICATE_HEADER AUTHENTICATE_HEADER}.
     * <p/>
     * The header value constructed is equal to:
     * <p/>
     * <code>{@link #getAuthcScheme() getAuthcScheme()} + " realm=\"" +
     * {@link #getApplicationName() getApplicationName()} + "\"";</code>
     *
     * @param request  incoming ServletRequest, ignored by this implementation
     * @param response outgoing ServletResponse
     * @return false - this sends the challenge to be sent back
     */
    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        logger.info("Authentication required: sending 401 Authentication challenge response.");

        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String authcHeader = getAuthcScheme() + " realm=\"" + getApplicationName() + "\"";
        httpResponse.setHeader(AUTHENTICATE_HEADER, authcHeader);

        httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());

        ErrResponseResult result = ResponseBuildUtil.failed().code(ResponseCode.ERR_101000)
                .errMsg("Authentication Falid")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        //空值不参与序列化
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        try {
            PrintWriter writer = httpResponse.getWriter();
            writer.write(objectMapper.writeValueAsString(result));
            writer.flush();
        } catch (IOException e) {
            logger.error("sendChallenge json error");
            throw new RuntimeException(e);
        }

        return false;
    }
}


