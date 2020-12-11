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

//        标识重复数据放在那个位置
        int duplicatesIndex = nums.length - 1;

        for (int i = 0; i < duplicatesIndex; i++) {

            for (int j = i + 1; j < duplicatesIndex + 1; j++) {

                if (nums[i] == nums[j]) {
                    int tem = nums[j];
                    for (int k = j; k < duplicatesIndex ; k++) {
                        nums[k] = nums[k + 1];
                    }
                    nums[duplicatesIndex] = tem;
                    duplicatesIndex = duplicatesIndex - 1;
//                    将指针回退一
                    j = j - 1;
                }

            }

        }
        return duplicatesIndex + 1;
    }

    public static void main(String[] args) {
        int i = removeDuplicates(new int[]{1, 1, 2});
        System.out.println(i);

    }

}
