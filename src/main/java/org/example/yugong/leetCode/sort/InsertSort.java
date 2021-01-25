package org.example.yugong.leetCode.sort;

/**
 * 插入排序
 * @author qiaobao
 * @since 2020-01-24
 */
public class InsertSort {


    public int[] sortArray(int[] nums) {

        if (nums == null || nums.length < 2) {

        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (nums[i] > nums[j]) {
                    int tem = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tem;
                }
            }
        }

        return nums;
    }
}
