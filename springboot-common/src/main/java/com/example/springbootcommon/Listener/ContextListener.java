/**
 * projectName: Spring-boot
 * fileName: ContextListener.java
 * packageName: com.example.springboot.Listener
 * date: 2024-04-14 0:56
 */
package com.example.springbootcommon.Listener;

//import com.example.springbootservice.Student;
//import jakarta.servlet.ServletContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author: QSky
 * @data: 2024-04-14 0:56
 **/
@Log4j2
@Component
public class ContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("ContextRefreshedEvent事件已触发");
        // 获取应用程序上下文对象
//        var applicationcontext = contextRefreshedEvent.getApplicationContext();
//        // 通过应用程序上下文对象获取Student对象
//        var student = applicationcontext.getBean(Student.class);
//        var appContext = applicationcontext.getBean(ServletContext.class);
//        student.setName("张三");
//        student.setAge(18);
//        student.setStudentNumber("20230001");
//        String message = student.toString();
//        appContext.setAttribute("student", message);
        log.info("资源初始化完成");
    }
}