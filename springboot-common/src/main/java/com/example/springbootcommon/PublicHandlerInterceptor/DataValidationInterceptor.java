package com.example.springbootcommon.PublicHandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: QSky
 * @date: 2024/4/22 下午3:09
 * @project: Spring-boot
 * @package: com.example.springbootcommon.PublicHandlerInterceptor
 */

/**
 * 数据验证拦截器，用于在处理请求之前验证用户是否已登录。
 * 实现HandlerInterceptor接口，提供preHandle方法用于前置拦截。
 */
@Log4j2
@Component
public class DataValidationInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前执行，用于验证用户是否已登录。
     *
     * @param request  HttpServletRequest对象，代表客户端的HTTP请求
     * @param response HttpServletResponse对象，用于向客户端发送响应
     * @param handler  将要执行的处理器对象
     * @return boolean 返回false时，拦截请求；返回true时，放行请求
     * @throws Exception 抛出异常时，会终止当前请求的处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("preHandle执行了");
        // 从会话中获取用户名和密码，验证其是否为空

        var session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // 如果session或用户名为空，则设置响应状态为400（BadRequest），并重定向到登录界面
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect(request.getContextPath() + "/user/login.html");
            return false;
        }
        return true;
    }

    /**
     * 请求处理完成后执行，但视图渲染前。
     *
     * @param request      HttpServletRequest对象
     * @param response     HttpServletResponse对象
     * @param handler      处理器对象
     * @param modelAndView 视图和模型数据的对象
     * @throws Exception 抛出异常时，不影响后续处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 执行后处理，记录日志
        log.info("postHandle执行了");
    }

    /**
     * 请求处理完成后，视图渲染完成后执行。
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param handler  处理器对象
     * @param ex       异常对象，如果处理过程中抛出异常则为异常对象，否则为null
     * @throws Exception 抛出异常时，不影响后续处理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 执行完成处理，记录日志
        log.info("afterCompletion执行了");
    }
}

