package org.example.yugong.spring.service;

import org.example.yugong.exception.CustomException;
import org.example.yugong.spring.entity.User;
import org.example.yugong.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qiaobao
 * @since 2021-04-19
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void update(User user) {
        user.setName("test2");
        userRepository.save(user);
        throw new CustomException("测试嵌套事务");
    }

}
