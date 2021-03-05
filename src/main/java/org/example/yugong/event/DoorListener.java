package org.example.yugong.event;

import java.util.EventListener;

/**
 * @author qiaobao
 * @since 2021-03-05
 */
public interface DoorListener extends EventListener {

    void DoorEvent(DoorEvent doorEvent);

}
