/**
 * projectName: Spring-boot
 * fileName: WebMvcConfig.java
 * packageName: com.example.springboot.Config
 * date: 2024-04-03 14:36
 */
package com.example.springbootcommon.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: QSky
 * @date: 2024-04-03 14:36
 **/

@Configuration
public class webMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}