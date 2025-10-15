package org.example.leetcode.october;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author qiaobao
 * @since 2025/10/11
 */
public class Eleven {
    public long maximumTotalDamage(int[] power) {
        // 这道题目更像是打家劫舍，这里的区别就是选中 power[i]后的限制不是按照，数组中下标，而是咒语的伤害值来确定限制
        int ans = 0;
        // 所以这里首先要做咒语的数据进行排序
        Arrays.sort(power);
        // 相同值的咒语是可以被重复选中的，应该要合并计算，所以这里要对元素进行去重
        List<Integer> powerVals =  new ArrayList<>();
        HashMap<Integer,Integer> powerCount = new HashMap<>();
        for(int i = 1; i < power.length; i++){
            if (i == 0 || power[i] != power[i-1]){
                // 排序的后第一个元素直接放入集合是一个新的值
                powerVals.add(power[i]);
                powerCount.put(power[i],1);
            }else{
                System.out.println(power[i]);
                System.out.println(i);
                int old = powerCount.get(power[i]);
                powerCount.put(power[i],old+1);
            }
        }


        return ans;
    }
}
