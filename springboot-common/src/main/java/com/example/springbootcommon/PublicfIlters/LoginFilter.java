package com.example.springbootcommon.PublicfIlters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author: QSky
 * @date: 2024/4/18 下午3:48
 * @project: Spring-boot
 * @package: com.example.springbootcommon.PublicfIlters
 */

@Log4j2
@Component
public class LoginFilter implements Filter {
    // 定义需要过滤的URL地址数组
    private final String[] urls = {
            "/login.html"
    };

    /**
     * 过滤器初始化方法。
     * 该方法在过滤器实例化后立即调用，用于执行过滤器的初始化逻辑。
     *
     * @param filterConfig 过滤器配置对象，提供了配置文件中定义的过滤器参数。
     * @throws ServletException 如果初始化过程中出现Servlet相关异常，则抛出。
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化login过滤器");
    }

    /**
     * 执行过滤逻辑的方法。
     * 该方法会在每个请求到达时被调用，用于判断请求是否需要进行过滤处理。
     *
     * @param servletRequest  客户端请求对象，包含了客户端发送的请求信息。
     * @param servletResponse 用于向客户端发送响应的对象。
     * @param filterChain     过滤器链对象，用于将请求传递给下一个过滤器或servlet。
     * @throws IOException      如果处理请求或响应时发生IO异常，则抛出。
     * @throws ServletException 如果处理请求或响应时发生Servlet相关异常，则抛出。
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("login过滤器开始执行");
        List<String> list = new ArrayList<>(Arrays.asList(urls)); // 将URL数组转换为List方便后续操作
        var httpServletRequest = (HttpServletRequest) servletRequest;
        var url = httpServletRequest.getRequestURL(); // 获取请求的完整URL
        String regex = "/[^/]+\\.html$"; // 定义匹配HTML文件的正则表达式
        var pattern = Pattern.compile(regex); // 编译正则表达式为Pattern对象
        var matcher = pattern.matcher(url); // 创建Matcher对象用于匹配URL
        if (matcher.find()) { // 如果URL中存在匹配的部分
            if (list.contains(matcher.group())) { // 检查是否为需要过滤的URL
                filterChain.doFilter(servletRequest, servletResponse); // 继续过滤链
            } else {
                log.warn("访问路径错误 {}", url); // 记录非法访问尝试
            }
        }
        log.info("login过滤器执行结束");
    }

    /**
     * 过滤器销毁方法。
     * 该方法在应用停止时调用，用于执行过滤器的销毁逻辑，释放资源。
     */
    @Override
    public void destroy() {
        log.info("销毁login过滤器");
    }
}

