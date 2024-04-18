/**
 * projectName: Spring-boot
 * fileName: Student.java
 * packageName: com.example.springboot
 * date: 2024-04-01 16:58
 */
package com.example.springbootservice;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: QSky
 * @data: 2024-04-01 16:58
 **/

@Setter
@Getter
@ToString
@Service
public class Student {
    private String name;
    private Integer age;
    private String studentNumber;
}

