package com.example.springbootapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

@SpringBootTest
class SpringbootApiApplicationTests {

    @Test
    void contextLoads() {
        var input = "http://localhost:8080/user/login.html";
        var regex = "/[^/]+\\.html$";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.println(matcher.group());
        } else {
            System.out.println("没有匹配到");
        }
    }
}