package org.example.yugong.leetCode;

/**
 * @author qiaobao
 * @since 2024/4/9
 */
public class AllNumsZero {

    public static boolean checkArray(int[] nums, int k) {
        int length = nums.length;
        int[] diff = new int[length];
        diff[0] = nums[0];
        for (int i = 1; i < length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }

        for (int i = 0; i < length; i++) {
            if (diff[i] == 0) {
                continue;
            }
            if (diff[i] < 0) {
                return false;
            }
            if (i + k > length) {
                return false;
            }
            int cur = diff[i];
            diff[i] = 0;
            diff[i + k] = diff[i + k] + cur;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] test = new int[]{2, 2, 3, 1, 1, 0};
        boolean b = checkArray(test, 3);
        System.out.println(b);

    }
}


