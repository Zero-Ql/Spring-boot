package com.example.springbootdao.Method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;


/**
 * @author: QSky
 * @date: 2024/5/23 下午4:26
 * @project: Spring-boot
 * @package: com.example.springbootdao.Method
 */

public class SelectAllMethod extends AbstractMethod {

    /**
     * 构造函数，用于创建一个SelectAllMethod对象。
     *
     * @param methodName 要映射的方法名。
     */
    public SelectAllMethod(String methodName) {
        super(methodName);

    }

    /**
     * 重写injectMappedStatement方法，用于生成并返回一个MappedStatement对象，它包含了查询全部数据的SQL语句。
     *
     * @param mapperClass 生成的mapper接口所在的类。
     * @param modelClass  数据模型类，即实体类。
     * @param tableInfo   表信息，包含了表的名称等信息。
     * @return 返回一个MappedStatement对象，包含了完整的查询全部数据的SQL语句及其相关配置。
     */
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        // 构造查询全部列的SQL语句
        String sql = "select * from " + tableInfo.getTableName();
        String methodName = "selectAll";

        // 使用语言驱动创建SQL源，根据SQL语句和模型类
        var sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);

        // 添加并返回一个用于查询全部数据的MappedStatement
        return this.addSelectMappedStatementForOther(mapperClass, methodName, sqlSource, modelClass);
    }
}
