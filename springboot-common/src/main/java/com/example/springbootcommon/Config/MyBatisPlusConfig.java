package com.example.springbootcommon.Config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author: QSky
 * @date: 2024/5/15 上午10:24
 * @project: Spring-boot
 * @package: com.example.springbootcommon.Config
 */

/**
 * MyBatisPlus配置类，用于配置MyBatisPlus的相关设置。
 *
 * @Configuration 标注为Spring配置类。
 * @MapperScan("com.example.*") 扫描指定包下的Mapper接口。
 */
@Configuration
@MapperScan("com.example.*")
public class MyBatisPlusConfig {

    /**
     * 创建并配置MybatisPlusInterceptor拦截器。
     *
     * @return 返回配置好的MybatisPlusInterceptor实例。
     */

    @Resource
    private DataSource dataSource;// 通过@Resource注解自动注入数据源。该数据源将用于数据库连接。

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        var interceptor = new MybatisPlusInterceptor(); // 创建MybatisPlusInterceptor实例
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 添加分页插件拦截器
        return interceptor; // 返回配置好的拦截器实例
    }

    /**
     * 配置并返回MybatisSqlSessionFactoryBean实例。
     * 这个工厂Bean用于创建Mybatis的SqlSessionFactory，它是Mybatis的核心组件，
     * 提供了与数据库交互的能力。
     *
     * @return MybatisSqlSessionFactoryBean配置实例
     */
    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
        // 创建MybatisSqlSessionFactoryBean实例
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        // 设置数据源，数据源是Mybatis与数据库交互的基础，这里将之前配置的数据源设置给SqlSessionFactoryBean
        mybatisPlus.setDataSource(dataSource);
        // 返回配置好的MybatisSqlSessionFactoryBean实例
        return mybatisPlus;
    }

}

