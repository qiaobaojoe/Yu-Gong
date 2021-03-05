package org.example.yugong.spring.event;

import org.example.yugong.spring.entity.User;
import org.example.yugong.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author qiaobao
 * @since 2021-03-05
 */

@Component
public class UserInsertListener {

    @Autowired
    private UserRepository userRepository;

    @EventListener
    @Order(2)
    public void onApplicationEvent(UserRegisterEvent event) {
        User user = event.getUser();
        userRepository.save(user);
        System.out.println("用户数据保存");
    }
}
