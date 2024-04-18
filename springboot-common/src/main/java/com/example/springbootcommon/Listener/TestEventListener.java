/**
 * projectName: Spring-boot
 * fileName: TestEventListener.java
 * packageName: com.example.springboot.Listener
 * date: 2024-04-14 0:58
 */
package com.example.springbootcommon.Listener;

import com.example.springbootcommon.Event.TestEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: QSky
 * @date: 2024-04-14 0:58
 **/
/**
 * 测试事件监听器类，用于监听TestEvent事件。
 * 使用Log4j2进行日志记录，并组件化以便于Spring容器管理。
 */
@Log4j2
@Component
public class TestEventListener implements ApplicationListener<TestEvent> {

    /**
     * 当监听到TestEvent事件时，处理该事件。
     *
     * @param event 发生的TestEvent事件，包含需要处理的学生信息。
     */
    @Override
    public void onApplicationEvent(TestEvent event) {
        // 从事件中获取学生信息，并记录到日志中
        var student = event.getStudent();
        log.info("信息：{}", student);
    }
}
