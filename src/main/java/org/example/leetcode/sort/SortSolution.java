package org.example.leetcode.sort;

/**
 * @author qiaobao
 * @since 2025/6/28
 */
public class SortSolution {

    public int[] selectSort(int[] nums) {
        // 重新复习一下几个简单排序的实现，最后尝试一下经典的快排
        // 选择排序优化结构
        int n = nums.length;
        // 边界条件判断
        if (n == 0 || n == 1) {
            return nums;
        }
        // 选择最大的元素，放在队尾
        for (int i = n; i > 0; i--) {
            int maxIndex = 0;
            for (int j = 1; j < i; j++) {
                if (nums[j] > nums[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap(nums, maxIndex, i - 1);
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            // 位置相同不需要处理
            return;
        }
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    public static void main(String[] args) {
//        SortSolution solution = new SortSolution();
//        solution.selectSort(new int[]{5, 1, 2, 3});

        int pow = (int) Math.pow(2, 6);
        System.out.println( pow);

    }
}
