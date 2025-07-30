package org.example.leetcode.july;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qiaobao
 * @since 2025/7/28
 */
public class TwentyEight {

    public int countMaxOrSubsets(int[] nums) {
        // 异或的最大值max,数据组所有的元素求异或值
        int max = 0;
        for (int num : nums) {
            max |= num;
        }
        int n = nums.length;
        double pow = Math.pow(2, n);
        ArrayList<ArrayList<Integer>> list = new ArrayList<>((int) pow);
        // 首先是第一种思路，暴力解法，我可以把所以的子集枚举出来然后求和
//        第一个元素选和不选两种方式，不选就不用，选的话要初始化数据
        list.add(new ArrayList<>(Collections.singletonList(nums[0])));
        list.add(new ArrayList<>(Collections.emptyList()));

        for (int i = 1; i < n; i++) {
//            当前元素可以选择添加也可以选择不填加到列表汇总
            int size = list.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> old = list.get(j);
                ArrayList<Integer> addCur = new ArrayList<>(old);
                addCur.add(nums[i]);
                list.add(addCur);
            }
        }

        return list.size();

    }

    public static void main(String[] args) {
        double pow = Math.pow(2, 16);
        System.out.println(pow * 4 / 1024);
        TwentyEight twentyEight = new TwentyEight();
        int i = twentyEight.countMaxOrSubsets(new int[]{3, 2, 1, 5});
        System.out.println(i);
    }
}
