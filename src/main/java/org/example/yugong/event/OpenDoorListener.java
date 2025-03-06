package org.example.yugong.event;

import com.google.common.collect.Lists;
import io.swagger.models.auth.In;

import java.util.Arrays;
import java.util.List;

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


    public static int minimumRefill(List<Integer> plants, int capacityA, int capacityB) {
        int refillSum = 0;
        int aIndex = 0;
        int bIndex = plants.size() -1;
        int aCap = capacityA;
        int bCap = capacityB;

        while (aIndex < bIndex) {
            waterPlant(plants, capacityA, refillSum, aIndex, aCap);
            aIndex ++;
            waterPlant(plants, capacityB, refillSum, bIndex, bCap);
            bIndex--;
        }

        if (aIndex == bIndex) {
            if (bCap > aCap) {
                waterPlant(plants, capacityB, refillSum, bIndex, bCap);
            }else {
                waterPlant(plants, capacityA, refillSum, aIndex, aCap);
            }
        }
        return refillSum;
    }

    public static void waterPlant(List<Integer> plants, int capacity, int sum, int index,int curCap) {
        if (plants.get(index) > curCap) {
            sum++;
            curCap = capacity;
        }
        curCap -= plants.get(index);

    }
    public static void main(String[] args) {
        Integer[] plant = {2, 2, 3, 3};
        System.out.println(minimumRefill(Arrays.asList(plant), 5, 5));

    }
}
