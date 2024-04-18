/**
 * projectName: Spring-boot
 * fileName: WebMvcConfig.java
 * packageName: com.example.springboot.Config
 * date: 2024-04-03 14:36
 */
package com.example.springbootcommon.Config;

import com.example.springbootcommon.PublicfIlters.AllFilter;
import com.example.springbootcommon.PublicfIlters.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: QSky
 * @date: 2024-04-03 14:36
 **/

@Configuration
class webMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public FilterRegistrationBean<AllFilter> allFilterRegistrationBean() {
        FilterRegistrationBean<AllFilter> allFilterBean = new FilterRegistrationBean<>(new AllFilter());
        allFilterBean.addUrlPatterns("/*");
        allFilterBean.addInitParameter("allowedMethods", "GET,POST");
        return allFilterBean;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilterRegistrationBean() {
        FilterRegistrationBean<LoginFilter> loginFilterBean = new FilterRegistrationBean<>(new LoginFilter());
        loginFilterBean.addUrlPatterns("/login");
        loginFilterBean.addInitParameter("allowedMethods", "GET,POST");
        return loginFilterBean;
    }
}