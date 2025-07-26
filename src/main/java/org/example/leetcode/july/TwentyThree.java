package org.example.leetcode.july;

import java.util.Arrays;

/**
 * @author qiaobao
 * @since 2025/7/23
 */
public class TwentyThree {

    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        // 迭代的方式  dp[i][j] = dp[i-coins[j]][j] + dp[i][j+1]
        int[][] dp = new int[amount + 1][coins.length];
        // 这里要限定不能选 当前索引下标之前的硬币，避免相同数组，顺序不一致重复计数的问题
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= amount; i++) {
            // 处理只能选择第一个硬币的情况
            if (i % coins[0] == 0) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }
        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j < coins.length; j++) {
                // j代表的是，硬币可以选择的范围 0 代表只能选择一个,就是coins[0]
                // 硬币币值大于i,这个时候没得选 = 0
                int res = 0;
                res += dp[i][j - 1];
                if (i >= coins[j]) {
                    res += dp[i - coins[j]][j];
                }
                dp[i][j] = res;
            }
        }
        return dp[amount][coins.length - 1];

    }

    public static void main(String[] args) {
        TwentyThree twentyThree = new TwentyThree();
        int change = twentyThree.change(5, new int[]{1, 2, 5});
        System.out.println(change);
    }
}
