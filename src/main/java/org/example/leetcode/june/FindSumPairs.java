package org.example.leetcode.june;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiaobao
 * @since 2025/7/6
 */
public class FindSumPairs {
    private int[] nums1;
    private HashMap<Integer, Integer> valCount1;

    private int[] nums2;
    private HashMap<Integer, Integer> valCount2;


    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.valCount1 = generateMap(nums1);
        this.nums2 = nums2;
        this.valCount2 = generateMap(nums2);
    }

    private HashMap<Integer, Integer> generateMap(int[] nums) {
        // 给定一个初始容量，避免频繁扩容
        HashMap<Integer, Integer> valCount = new HashMap<>(nums.length / 2 + 1);
        for (int a : nums) {
            int c = valCount.getOrDefault(a, 0);
            valCount.put(a, c + 1);
        }
        return valCount;
    }

    public void add(int index, int val) {
        if (nums2 == null || index >= nums2.length) {
            // 数组为空和越界处理
            return;
        }
        int originVal = nums2[index];
        int originValCount = valCount2.get(originVal);
        valCount2.put(originVal, originValCount - 1);

        nums2[index] += val;
        int valCount = valCount2.getOrDefault(nums2[index], 0) + 1;
        valCount2.put(nums2[index], valCount);
    }

    public int count(int tot) {
        if (nums2 == null || nums1 == null) {
            // 数组为空和越界处理
            return 0;
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : valCount1.entrySet()) {
            int val = entry.getKey();
            int valCount = entry.getValue();
            int pairVal = tot - val;
            int pairValCount = valCount2.getOrDefault(pairVal, 0);
            if (pairValCount == 0) {
                continue;
            }
            System.out.println("tot=" + tot + ":" + val + "-" + valCount + "-" + pairVal + "-" + pairValCount);
            ans += (valCount * pairValCount);
        }
        return ans;
    }

    public static void main(String[] args) {
        FindSumPairs findSumPairs = new FindSumPairs(
                new int[]{1, 1, 2, 2, 2, 3}, new int[]{1, 4, 5, 2, 5, 4}
        );
        System.out.println(findSumPairs.count(7));
        findSumPairs.add(3, 2);
        System.out.println(findSumPairs.count(8));
        System.out.println(findSumPairs.count(4));
        findSumPairs.add(0, 1);
        findSumPairs.add(1, 1);
        System.out.println(findSumPairs.count(7));

    }
}
