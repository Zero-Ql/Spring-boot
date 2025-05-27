package com.example.springbootapi.controller;

import com.example.springbootcommon.PublicResult.Result;
import com.example.springbootservice.Iservice.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: QSky
 * @date: 2024/5/15 上午11:24
 * @project: Spring-boot
 * @package: com.example.springbootapi.controller
 */

public class MyBatisPlusTest {
}

@Log4j2
@RestController
@RequestMapping(path = "/MyBatisPlus")
class getUser {

    @Resource
    private UserServiceImpl userServiceimpl;

    @RequestMapping(path = "/test1", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUser1() {
//        var list = Result.success(userServiceimpl.findAllUser());
//        log.info(list);
//        return list.toString();
        return "";
    }

    @RequestMapping(path = "/test2", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUser2() {
        var list = userServiceimpl.findUsersByAgeGreaterThan(19);
        list.forEach(log::info);
        return Result.success(list).toString();
    }
}
