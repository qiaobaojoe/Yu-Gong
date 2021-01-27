package org.example.yugong.leetCode.sort;

/**
 * 希尔排序是插入排序的变种
 * 分为治之的方法
 * @author qiaobao
 * @since 2021-01-27
 */
public class ShellSort {


    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }

        int length = nums.length;
        int gap = length;

        while (gap != 1) {
            gap = gap / 2;

            for (int i = 0; i < gap; i++) {
                for (int j = i; j < length; j = j + gap) {
                    if (j > gap - 1) {
                        int startIndex = i;
                        while (startIndex < j) {
                            if (nums[j] < nums[startIndex]) {
                                insertNum(nums, gap, j, startIndex);
                            }
                            startIndex = startIndex + gap;

                        }
                    }

                }
            }
        }

        return nums;

    }

    private void insertNum(int[] nums, int gap, int endIndex, int insertIndex) {

        int tem = nums[endIndex];
        while (endIndex > insertIndex) {
            nums[endIndex] = nums[endIndex - gap];
            endIndex = endIndex - gap;
        }
        nums[insertIndex] = tem;
    }


    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] sortArray = shellSort.sortArray(new int[]{5, 1, 1, 2, 0, 0});
        for (int i = 0; i < sortArray.length; i++) {
            System.out.println(sortArray[i]);
        }


    }





}
