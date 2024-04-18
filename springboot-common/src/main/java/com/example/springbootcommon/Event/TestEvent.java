/**
 * projectName: Spring-boot
 * fileName: TestEvent.java
 * packageName: com.example.springboot
 * date: 2024-04-14 0:51
 */
package com.example.springbootcommon.Event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEvent;

/**
 * TestEvent 类扩展了 ApplicationEvent 用于表示一个测试事件。
 * 这个类主要用于事件驱动的架构中，用来传递与学生相关的事件信息。
 */
@Getter
@Setter
@ToString
@Log4j2
public class TestEvent extends ApplicationEvent {
    private String student; // 学生信息

    /**
     * 构造函数，用于创建一个 TestEvent 实例。
     *
     * @param source 事件的来源对象。
     * @param student 发生事件的学生信息。
     */
    public TestEvent(Object source, String student) {
        super(source); // 调用父类构造函数，设置事件来源
        this.student = student; // 设置事件相关的学生信息
    }
}
