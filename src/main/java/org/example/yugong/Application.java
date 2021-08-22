package org.example.yugong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author qiaobao
 * @since 2021-03-04
 */
@SpringBootApplication
@EnableAsync
@EnableRetry
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.example.yugong")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
