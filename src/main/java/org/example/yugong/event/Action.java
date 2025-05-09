package org.example.yugong.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaobao
 * @since 2021-03-05
 */
public class Action {

    public static List<DoorListener> getAllListener() {
        List<DoorListener> list = new ArrayList<>();
        list.add(new OpenDoorListener());
        list.add(new CloseDoorListener());
        return list;
    }

    public static void main(String[] args) {
        DoorEvent open = new DoorEvent("open", 1);
        List<DoorListener> listeners = getAllListener();
        for (DoorListener listener : listeners) {
            listener.DoorEvent(open);
        }
    }
}
