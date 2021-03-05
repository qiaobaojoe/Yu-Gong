package org.example.yugong.event;

import java.util.EventObject;

/**
 * @author qiaobao
 * @since 2021-03-05
 */
public class DoorEvent extends EventObject {

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DoorEvent(Object source) {
        super(source);
    }


    public DoorEvent(Object source, Integer status) {
        super(source);
        this.status = status;
    }
}
