package com.example.springbootcommon.PublicfIlters;

import jakarta.servlet.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: QSky
 * @date: 2024/4/18 下午1:21
 * @project: Spring-boot
 * @package: com.example.springbootapi.PublicfIlters
 */

@Log4j2
@Component
public class AllFilter implements Filter {
    /**
     * 过滤器的初始化方法。
     *
     * @param filterConfig 过滤器配置对象，提供了配置信息。
     * @throws ServletException 如果初始化过程中出现异常，则抛出ServletException。
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器");
    }

    /**
     * 过滤器的核心方法，用于拦截请求并进行处理。
     * 如果请求来自本机，则放行；否则，返回403禁止访问。
     *
     * @param servletRequest  代表客户端的请求
     * @param servletResponse 用于向客户端发送响应
     * @param filterChain     过滤器链，用于将请求传递给下一个过滤器或servlet
     * @throws IOException      如果发生输入/输出错误
     * @throws ServletException 如果处理请求时发生异常
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器开始工作");

        // 获取客户端IP地址
        String ip = servletRequest.getRemoteHost();

        // 判断IP是否为本机，是则放行，否则返回 403 错误
        if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 设置响应编码和类型，写入禁止访问的消息
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("application/json;charset=UTF-8");
            servletResponse.getWriter().write("{\"code\":\"403\",\"msg\":\"非本机IP，禁止访问\"}");
            log.error("{} 非本机IP，禁止访问", ip);
        }

        log.info("过滤器结束工作");
    }

    /**
     * 过滤器的销毁方法，用于执行过滤器的清理工作。
     */
    @Override
    public void destroy() {
        log.info("销毁过滤器");
    }
}
