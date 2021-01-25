package org.example.yugong.leetCode.sort;

/**
 * 插入排序
 * @author qiaobao
 * @since 2020-01-24
 */
public class InsertSort {


    public int[] sortArray(int[] nums) {

        if (nums == null || nums.length < 2) {
            return nums;
        }
        for (int i = 0; i < nums.length -1 ; i++) {
            int insertIndex = i + 1;
            for (int j = 0; j < i + 1; j++) {
                if (nums[i + 1] < nums[j]) {
                    insertIndex = j;
                    break;
                }
            }
            if (insertIndex != i + 1) {
                int tem = nums[i + 1];
                System.arraycopy(nums, insertIndex, nums, insertIndex + 1, i + 1 - insertIndex);
                nums[insertIndex] = tem;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        int[] sortArray = insertSort.sortArray(new int[]{-1,2,-8,-10});
        for (int i = 0; i < sortArray.length; i++) {
            System.out.println(sortArray[i]);
        }
    }

    /**
     * 和插入排序还是有区别的，这里的不再是数组位置的交换，而是整体的异动
     * O(n2)
     * 内存消耗 1 ，原地排序
     * 不稳定
     *
     */

}
