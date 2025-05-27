package com.example.springbootapi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootdao.Mapper.UserMapper;
import com.example.springbootdao.pojo.User;
import com.example.springbootservice.Iservice.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootApiApplicationTests {
    // 注入UserMapper，用于测试中对用户数据的查询操作
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * 测试UserMapper的查询功能
     * 该方法没有参数
     * 没有返回值，但会通过打印输出的方式展示查询结果
     */
    @Test
    public void test_Select() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User user = new User();
        user.setAge(21);
        queryWrapper.lambda().eq(User::getId, "24");
        var list = userMapper.update(user, queryWrapper);
        System.out.println(list);
    }

    @Test
    public void test_findUsersByAgeGreaterThan() {
        List<User> list = userServiceImpl.findUsersByAgeGreaterThan(18);
        list.forEach(System.out::println);
    }

    @Test
    public void test_saveUser() {
//        var b = userServiceImpl.saveUser(3L, "满惠", 19, "test3@baomidou.com");
        var b = userServiceImpl.insert(new User(20L, "文梦", 22, "test20@baomidou.com"));
        System.out.println(b);
    }

    @Test
    public void test_saveBatchUser() {
        List<User> userList = Arrays.asList(new User(19L, "聂逸馨", 24, "test19@baomidou.com"), new User(20L, "QSky", 21, "test20@baomidou.com"));
        var a = userServiceImpl.saveBatchUser(userList, 2);
        System.out.println(a);
    }

    @Test
    public void test_saveOrUpdateUser() {
        var b = userServiceImpl.removeQueryWrapper();
//        List<Integer> iDs = Arrays.asList(9, 10);
//        var b = userServiceImpl.removeByIDs(iDs);
        System.out.println(b);
    }

    @Test
    public void test_saveOrUpdateBatchUser() {
        List<User> userList = Arrays.asList(new User(17L, "弓天心", 25, "test17@baomidou.com"), new User(18L, "宦紫云", 22, "test18@baomidou.com"));
        var b = userServiceImpl.saveOrUpdateBatchUser(userList, 2);
        System.out.println(b);
    }

    @Test
    public void test_lists() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        var b = userServiceImpl.lists(queryWrapper.gt("age", 20));
        var b = userServiceImpl.getMaps();
        b.forEach((map -> map.forEach((key, value) -> System.out.println(key + ":" + value))));
    }

    @Test
    public void test_getPages() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "满惠");
        var b = userServiceImpl.getPages(queryWrapper);
        b.forEach(user -> user.forEach((k, v) -> System.out.println(k + ":" + v)));
    }

    @Test
    public void test_Injector() {
        List<User> users = userMapper.selectByAll("user", null);
        users.forEach(System.out::println);
    }

    @Test
    public void test_11111() {

    }

}