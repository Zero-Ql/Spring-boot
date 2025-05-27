package com.example.springbootdao.Mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.springbootdao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserMapper接口，用于映射用户数据。
 * 继承自BaseMapper<User>，提供基本的CRUD操作。
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 选择所有用户。
     *
     * @return 返回用户列表。
     */
    List<User> selectAll();

    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectByAll(String tableName, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}

