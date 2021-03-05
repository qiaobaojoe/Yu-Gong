package org.example.yugong.spring.event;

import org.example.yugong.exception.CustomException;
import org.example.yugong.spring.entity.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author qiaobao
 * @since 2021-03-05
 */
@Component
public class RetryTestListener  {


    @EventListener
    @Async("userExecutorsPool")
    @Retryable(value = CustomException.class)
    public void onApplicationEvent(UserRegisterEvent event) {

        User user = event.getUser();
        String name = Thread.currentThread().getName();
        System.out.println("当前线程" + name);
        if (user.getAge() / 2 == 0) {
            System.out.println("重试任务");
            throw new CustomException("retry 重试错误");
        } else {
            throw new NullPointerException();
        }
    }
}
