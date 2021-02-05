package org.example.yugong.leetCode.dp;

/**
 * 求解最长递增子序列
 *
 * @author qiaobao
 * @since 2021-02-05
 */
public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {

        int maxLength = 0;

        int size = nums.length;

        for (int i = 0; i < size - 1; i++) {

            int[] tem = new int[size];
            int temSize = 0;

            for (int j = i + 1; j < size; j++) {
                if (nums[j] > nums[i]) {

                    if (temSize == 0) {
                        tem[temSize] = nums[j];
                        temSize++;
                    }
                    if (nums[j] < tem[temSize]) {
                        tem[temSize - 1] = nums[j];
                    } else {
                        tem[temSize] = nums[j];
                        temSize++;
                    }
                }
            }
            if (temSize == 0) {
                continue;
            }
            if (temSize > maxLength) {
                maxLength = temSize;
            }
        }

        return maxLength;

    }
}
