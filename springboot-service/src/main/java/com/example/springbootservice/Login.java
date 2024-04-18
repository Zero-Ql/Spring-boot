/**
 * projectName: Spring-boot
 * fileName: Login.java
 * packageName: com.example.springboot.Service
 * date: 2024-04-11 14:16
 */
package com.example.springbootservice;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author: QSky
 * @data: 2024-04-11 14:16
 **/
@Service
public class Login {
    public static boolean login(HashMap<String, String> hashMap) {
        if (hashMap.get("username").equals("admin") && hashMap.get("password").equals("123456")) {
            return true;
        }
        return false;
    }
}