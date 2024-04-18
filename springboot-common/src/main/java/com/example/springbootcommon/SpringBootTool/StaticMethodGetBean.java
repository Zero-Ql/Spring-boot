package com.example.springbootcommon.SpringBootTool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: QSky
 * @date: 2024/4/15 14:58
 * @project: Spring-boot
 * @package: com.example.springbootcommon.SpringBootTool
 */

@Component
public class StaticMethodGetBean<T> implements ApplicationContextAware {
    // Spring应用上下文，用于访问应用上下文中的bean
    private static ApplicationContext applicationContext;

    /**
     * 设置应用上下文。
     * @param applicationContext Spring应用上下文实例
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        StaticMethodGetBean.applicationContext = applicationContext;
    }

    /**
     * 通过类类型获取应用上下文中的bean。
     *
     * @param clazz 要获取的bean的类类型
     * @return 返回bean的实例，如果不存在则返回null
     * @param <T> bean的类型
     */
    public static <T> T getBean(Class<T> clazz) {
        // 尝试获取bean实例，如果不存在则返回null
        return applicationContext.getBean(clazz) != null ? applicationContext.getBean(clazz) : null;
    }
}

