package org.example.yugong.spring.event;

import org.example.yugong.spring.entity.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author qiaobao
 * @since 2021-03-05
 */
@Service
public class UserRegisterPublisherService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void insert(User user){
        UserRegisterEvent event = new UserRegisterEvent(user);
        applicationEventPublisher.publishEvent(event);
    }


}
