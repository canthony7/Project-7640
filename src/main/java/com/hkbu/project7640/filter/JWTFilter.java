package com.hkbu.project7640.filter;

import com.hkbu.project7640.dto.ResponseEnum;
import com.hkbu.project7640.entity.JwtToken;
import com.hkbu.project7640.exception.GlobalException;
import com.hkbu.project7640.exception.GlobalExceptionHandler;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chet
 * @date 12/4/2023 1:37 pm
 * @description jwt过滤器
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 拦截请求，调用该类的executeLogin方法，返回true则放行
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            executeLogin(request, response);
        } catch (Exception e) {
            response401(request, response);
        }
        return true;
    }
    // 调用Realm中的认证方法 (doGetAuthenticationInfo)
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 拿到请求头，得到token传给doGetAuthenticationInfo
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        System.out.println("executeLogin:" + token);
        JwtToken jwtToken = new JwtToken(token);
        SecurityUtils.getSubject().login(jwtToken);
        return true;
    }

    @Override
    protected boolean isLoginAttempt(String authzHeader) {
        return super.isLoginAttempt(authzHeader);
    }

    private void response401(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/401");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    // 跨域支持
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
