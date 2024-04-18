/**
 * projectName: Spring-boot
 * fileName: GetStudent.java
 * packageName: com.example.springboot.Service
 * date: 2024-04-14 0:46
 */
package com.example.springbootservice;

import com.example.springbootcommon.Event.TestEvent;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author: QSky
 * @date: 2024-04-14 0:46
 **/
@Service // 标示一个Spring框架的服务组件
public class GetStudent {
    @Resource // 注入ApplicationContext对象，用于访问Spring应用上下文
    private ApplicationContext applicationContext;

    @Resource // 注入Student对象，该对象将被用于演示事件发布时的数据载体
    private Student student;

    /**
     * 获取并设置学生信息，然后发布一个学生信息更新的事件。
     *
     * @return 返回设置好的学生信息字符串。
     */
    public String getStudent() {
        // 设置学生的基本信息
        student.setName("Tom");
        student.setAge(18);
        student.setStudentNumber("20240001");
        // 发布一个自定义事件，通知其他监听器学生信息已更新
        applicationContext.publishEvent(new TestEvent(this, student.toString()));
        // 返回学生信息
        return student.toString();
    }
}
