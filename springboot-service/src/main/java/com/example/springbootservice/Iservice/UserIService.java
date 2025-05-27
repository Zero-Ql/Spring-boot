package com.example.springbootservice.Iservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootdao.pojo.User;

import java.util.List;


public interface UserIService extends IService<User> {
    List<User> findUsersByAgeGreaterThan(int age);
}
