/**
 * projectName: Spring-boot
 * fileName: SessionListener.java
 * packageName: com.example.springboot.Listener
 * date: 2024-04-14 0:55
 */
package com.example.springbootcommon.Listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author: QSky
 * @data: 2024-04-14 0:55
 **/

/**
 * 使用Log4j2日志组件记录HttpSession事件的监听器类。
 * 该类实现了HttpSessionListener接口，用于监听会话的创建和销毁事件，
 * 以统计当前在线用户数量。
 */
@Log4j2
@Component
public class SessionListener implements HttpSessionListener {
    private Integer count = 0; // 记录当前在线用户数量

    /**
     * 当会话创建时被调用，用于记录日志并增加在线用户计数。
     *
     * @param httpSessionEvent 会话事件对象，包含会话相关信息。
     */
    @Override
    public synchronized void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info("在线用户+1");
        count++;
        // 将当前在线用户数量更新到Servlet上下文中
        httpSessionEvent.getSession().getServletContext().setAttribute("count", count);
    }

    /**
     * 当会话销毁时被调用，用于记录日志并减少在线用户计数。
     *
     * @param httpSessionEvent 会话事件对象，包含会话相关信息。
     */
    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("session已超时销毁\t在线用户-1");
        count--;
        // 将当前在线用户数量更新到Servlet上下文中
        httpSessionEvent.getSession().getServletContext().setAttribute("count", count);
    }
}