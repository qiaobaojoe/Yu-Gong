package org.example.yugong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qiaobao
 * @since 2021-03-04
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.example.yugong.spring")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
