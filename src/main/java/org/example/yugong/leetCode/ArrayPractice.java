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
//        int i = removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
        int maxProfit = maxProfit(new int[]{7,1,5,3,6,4});

        System.out.println(maxProfit);

    }

    public static int maxProfit(int[] prices) {
        // 操作点位
        int buyIndex = 0;
        // 当前是否持仓标识
        boolean holdFlag = false;

        int profit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (holdFlag) {
                if (prices[i] > prices[i + 1]) {
                    holdFlag = false;
                    profit = profit + (prices[i] - prices[buyIndex]);
                }else {
                    if (i == prices.length - 2) {
                        profit = profit + (prices[i+1] - prices[buyIndex]);
                        break;
                    }
                }
            } else {
                if (prices[i] < prices[i + 1]) {
                    buyIndex = i;
                    holdFlag = true;
                }
            }
        }
        return profit;
    }



}
