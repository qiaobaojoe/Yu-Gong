package org.example.yugong.spring.event;

import org.example.yugong.spring.entity.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qiaobao
 * @since 2021-03-05
 */
@Service
public class UserRegisterPublisherService   {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    public void insert(User user){
        UserRegisterEvent event = new UserRegisterEvent(user);
        applicationEventPublisher.publishEvent(event);
    }


}
