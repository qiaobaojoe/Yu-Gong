package org.example.yugong.event;

/**
 * @author qiaobao
 * @since 2021-03-05
 */
public class CloseDoorListener implements DoorListener {
    @Override
    public void DoorEvent(DoorEvent doorEvent) {
        Integer openStatus =  doorEvent.getStatus();
        if(0 == openStatus) {
            System.out.println("the door is close");
        }
    }
}
