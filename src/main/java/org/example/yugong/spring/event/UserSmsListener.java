package org.example.yugong.spring.event;

import org.example.yugong.spring.entity.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author qiaobao
 * @since 2021-03-05
 */
@Component
public class UserSmsListener{

    @EventListener
    @Order(1)
    public void onApplicationEvent(UserRegisterEvent event) {
        User user = event.getUser();
//        发送sms
        System.out.println("发送用户短信" + user.getName());
    }
}
