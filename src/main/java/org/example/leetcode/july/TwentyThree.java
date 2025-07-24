package org.example.leetcode.july;

import java.util.Arrays;

/**
 * @author qiaobao
 * @since 2025/7/23
 */
public class TwentyThree {

    private int[] coins;
    private int[][] memo;

    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        // 上手很直接的递归写法
        this.coins = coins;
        this.memo = new int[coins.length][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // 这里要限定不能选 当前索引下标之前的硬币，避免相同数组，顺序不一致重复计数的问题
        return dfs(amount, 0);

    }

    private int dfs(int amount, int startIndex) {
        if (memo[startIndex][amount] != -1) {
            return memo[startIndex][amount];
        }

        int ans = 0;
        for (int i = startIndex; i < coins.length; i++) {
            if (coins[i] > amount) {
                // 硬币的币值超过当前今天，不能兑换
                continue;
            }
            if (coins[i] == amount) {
                // 币值相等，直接兑换
                ans++;
                continue;
            }
            ans += dfs(amount - coins[i], i);
        }
        memo[startIndex][amount] = ans;
        return ans;
    }

    public static void main(String[] args) {
        TwentyThree twentyThree = new TwentyThree();
        int change = twentyThree.change(5, new int[]{1, 2, 5});
        System.out.println(change);
    }
}
