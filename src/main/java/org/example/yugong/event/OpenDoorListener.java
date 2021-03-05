package org.example.yugong.event;

/**
 * @author qiaobao
 * @since 2021-03-05
 */
public class OpenDoorListener implements DoorListener {

    @Override
    public void DoorEvent(DoorEvent doorEvent) {
        Integer openStatus = doorEvent.getStatus();
        if(1 == openStatus) {
            System.out.println("the door is open");
        }
    }
}
