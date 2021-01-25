package org.example.yugong.leetCode.sort;

/**
 * 冒泡排序
 * @author qiaobao
 * @since 2020-01-24
 */
public class BubbleSort {

    public int[] sortArray(int[] nums)  {

        if (nums == null || nums.length < 2) {
            return nums;
        }

        for (int i = nums.length; i > 0; i--) {
            int times = 0;
            for (int j = 0; j < i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tem = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tem;
                    times++;
                }
            }
            if (times == 0) {
                break;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] sortArray = bubbleSort.sortArray(new int[]{5, 1, 1, 2, 0, 0});
        for (int i = 0; i < sortArray.length; i++) {
            System.out.println(sortArray[i]);
        }
    }
}
