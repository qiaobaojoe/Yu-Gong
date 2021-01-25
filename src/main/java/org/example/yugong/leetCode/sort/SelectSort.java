package org.example.yugong.leetCode.sort;

/**
 * https://leetcode-cn.com/problems/sort-an-array/
 * 选择排序
 * @author qiaobao
 * @since 2021-01-25
 */
public class SelectSort {

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }

        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int tem = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = tem;
            }

        }

        return nums;
    }

    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        int[] sortArray = selectSort.sortArray(new int[]{5, 1, 1, 2, 0, 0});
        for (int i = 0; i < sortArray.length; i++) {
            System.out.println(sortArray[i]);
        }


    }

    /**
     * 区分每个排序的特点，四个维度
     * 排序算法的主要耗时  是交换数据
     * 时间复杂度:O(n2) 原地排序
     * 空间消耗：1 不占用内存
     * 稳定性：不稳定   最小：n  -- (n+1)*n/2
     */
}
