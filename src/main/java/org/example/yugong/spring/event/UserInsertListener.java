package org.example.yugong.spring.event;

import org.example.yugong.spring.entity.User;
import org.example.yugong.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author qiaobao
 * @since 2021-03-05
 */

@Component
public class UserInsertListener implements ApplicationListener<UserRegisterEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        User user = event.getUser();
        userRepository.save(user);
        System.out.println("用户数据保存");
    }
}
