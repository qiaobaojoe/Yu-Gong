package org.example.yugong.spring.event;

import org.example.yugong.spring.entity.User;
import org.springframework.context.ApplicationEvent;

/**
 * @author qiaobao
 * @since 2021-03-05
 */
public class UserRegisterEvent extends ApplicationEvent {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRegisterEvent(User source) {
        super(source);
        this.user = source;
    }
}
