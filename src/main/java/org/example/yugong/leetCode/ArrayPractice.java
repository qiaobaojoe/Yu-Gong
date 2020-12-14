package org.example.yugong.leetCode;

/**
 * @author qiaobao
 * @since 2020-12-11
 */
public class ArrayPractice {

    /**
     * 删除排序数组中的重复项,
     * 双指针
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int i = removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
        System.out.println(i);

    }

}
