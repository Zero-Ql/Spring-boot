package com.example.springbootcommon.PublicfIlters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: QSky
 * @date: 2024/4/18 下午3:48
 * @project: Spring-boot
 * @package: com.example.springbootcommon.PublicfIlters
 */

@Log4j2
@Component
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化login过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("login过滤器开始执行");
        var httpServletRequest = (HttpServletRequest) servletRequest;
        var url = httpServletRequest.getRequestURL();

        log.info(url);
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("login过滤器执行结束");
    }

    @Override
    public void destroy() {
        log.info("销毁login过滤器");
    }
}
