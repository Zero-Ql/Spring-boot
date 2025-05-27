package com.example.springbootapi;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Log4j2
@SpringBootApplication(scanBasePackages = {"com.example.*"})
public class SpringbootApiApplication {
    public static void main(String[] args) throws UnknownHostException {
//        SpringApplication.run(SpringbootApiApplication.class, args);
        ConfigurableApplicationContext application = SpringApplication.run(SpringbootApiApplication.class, args);
        System.out.println(
                """
                          ____  __  __    ___    ___     __    ____    ____ \s
                         /',__\\/\\ \\/\\ \\  /'___\\ /'___\\ /'__`\\ /',__\\  /',__\\\s
                        /\\__, `\\ \\ \\_\\ \\/\\ \\__//\\ \\__//\\  __//\\__, `\\/\\__, `\\
                        \\/\\____/\\ \\____/\\ \\____\\ \\____\\ \\____\\/\\____/\\/\\____/
                         \\/___/  \\/___/  \\/____/\\/____/\\/____/\\/___/  \\/___/\s
                        """);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(path)) {
            path = "";
        }
        log.info("\n----------------------------------------------------------\n\tApplication  is running! Access URLs:\n\tLocal访问网址: \t\thttp://localhost:{}{}\n\tExternal访问网址: \thttp://{}:{}{}\n\t----------------------------------------------------------", port, path, ip, port, path);
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        log.info("当前项目进程号：{}", jvmName.split("@")[0]);
    }
}
