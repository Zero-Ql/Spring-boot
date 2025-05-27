/**
 * projectName: Spring-boot
 * fileName: WebMvcConfig.java
 * packageName: com.example.springboot.Config
 * date: 2024-04-03 14:36
 */
package com.example.springbootcommon.Config;

import com.example.springbootcommon.PublicHandlerInterceptor.DataValidationInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: QSky
 * @date: 2024-04-03 14:36
 **/

@Configuration // 标识为配置类
public class webMvcConfig implements WebMvcConfigurer {

    @Resource
    private DataValidationInterceptor dataValidationInterceptor; // 注入数据验证拦截器

    /**
     * 配置静态资源处理器
     *
     * @param registry 资源处理器注册表
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/"); // 配置静态资源路径
    }

    /**
     * 添加拦截器
     *
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加数据验证拦截器，并配置拦截规则，排除特定路径
        registry.addInterceptor(dataValidationInterceptor)
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns(
                        "/MyBatisPlus/**", // 排除 MyBatisPlus 测试相关路径
                        "/user/**", // 排除登录页
                        "/**/*.html", // 排除所有HTML文件
                        "/**/*.js", // 排除所有JS文件
                        "/**/*.css" // 排除所有CSS文件
                );
    }
}
