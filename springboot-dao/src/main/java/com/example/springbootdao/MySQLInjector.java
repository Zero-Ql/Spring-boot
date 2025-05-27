package com.example.springbootdao;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.example.springbootdao.Method.SelectAllMethod;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: QSky
 * @date: 2024/5/23 下午4:46
 * @project: Spring-boot
 * @package: com.example.springbootdao
 */

/**
 * MySQLInjector类，继承自AbstractSqlInjector，用于生成MySQL数据库操作的方法列表。
 *
 * @@Repository 注解标识这是一个仓库层组件。
 */
@Repository
public class MySQLInjector extends AbstractSqlInjector {

    /**
     * 获取方法列表，根据配置、映射器类和表信息生成特定的方法列表。
     *
     * @param configuration MyBatis的配置信息。
     * @param mapperClass   映射器类，用于确定方法的生成范围和类型。
     * @param tableInfo     表信息，包含关于数据库表的结构和元数据。
     * @return 返回一个包含生成的方法列表的集合。
     */
    @Override
    public List<AbstractMethod> getMethodList(Configuration configuration, Class<?> mapperClass, TableInfo tableInfo) {
        // 调用父类方法，获取初始的方法列表
        List<AbstractMethod> methodList = super.getMethodList(configuration, mapperClass, tableInfo);
        // 向方法列表中添加一个自定义的selectAll方法
        methodList.add(new SelectAllMethod("selectAll"));
        return methodList;
    }
}
