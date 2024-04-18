/**
 * projectName: Spring-boot
 * fileName: Score.java
 * packageName: com.example.springboot.Service
 * date: 2024-04-14 1:16
 */
package com.example.springbootservice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Setter
@Getter
@ToString
@Service
public class Score {
    private String Chinese;
    private String Math;
    private String English;
}