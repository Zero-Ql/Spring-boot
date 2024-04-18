package com.example.springbootcommon.Config;

import com.example.springbootcommon.PublicfIlters.AllFilter;
import com.example.springbootcommon.PublicfIlters.LoginFilter;
import jakarta.annotation.Resource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: QSky
 * @date: 2024/4/18 下午7:48
 * @project: Spring-boot
 * @package: com.example.springbootcommon.Config
 */

@Configuration // 标识为配置类
public class FilterConfig {
    @Resource
    private AllFilter allFilter; // 注入AllFilter实例
    @Resource
    private LoginFilter loginFilter; // 注入LoginFilter实例

    /**
     * 配置AllFilter的注册信息。
     *
     * @return FilterRegistrationBean<AllFilter> 返回AllFilter的注册bean配置。
     */
    @Bean
    public FilterRegistrationBean<AllFilter> allFilterRegistrationBean() {
        FilterRegistrationBean<AllFilter> allFilterBean = new FilterRegistrationBean<>();
        allFilterBean.setFilter(allFilter); // 设置过滤器实例
        allFilterBean.addUrlPatterns("/*"); // 设置过滤器的URL模式
        allFilterBean.setName("allFilter"); // 设置过滤器的名称
        allFilterBean.setOrder(1); // 设置过滤器的执行顺序
        return allFilterBean;
    }

    /**
     * 配置LoginFilter的注册信息。
     *
     * @return FilterRegistrationBean<LoginFilter> 返回LoginFilter的注册bean配置。
     */
    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilterRegistrationBean() {
        FilterRegistrationBean<LoginFilter> loginFilterBean = new FilterRegistrationBean<>();
        loginFilterBean.setFilter(loginFilter); // 设置过滤器实例
        loginFilterBean.addUrlPatterns("*.html"); // 设置过滤器的URL模式
        loginFilterBean.setName("loginFilter"); // 设置过滤器的名称
        loginFilterBean.setOrder(2); // 设置过滤器的执行顺序
        return loginFilterBean;
    }
}

