package com.example.springbootapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApiApplicationTests {

    @Test
    void contextLoads() {

        var str = "hello";
        System.out.println(str.matches("[a-zA-Z]"));
    }
}
