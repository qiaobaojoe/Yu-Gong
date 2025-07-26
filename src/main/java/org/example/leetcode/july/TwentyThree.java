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
        if (amount < 0 || startIndex >= coins.length) {
            return 0;
        }
        if (amount == 0) {
            memo[startIndex][amount] = 1;
            return 1;
        }
        if (memo[startIndex][amount] != -1) {
            return memo[startIndex][amount];
        }
        int ans = 0;
        // 考虑下一种迭代的思路，当前只存在选择 i 和不选择i的情况
        ans += dfs(amount - coins[startIndex], startIndex);
        ans += dfs(amount, startIndex + 1);
        memo[startIndex][amount] = ans;
        return ans;
    }

    public static void main(String[] args) {
        TwentyThree twentyThree = new TwentyThree();
        int change = twentyThree.change(5, new int[]{1, 2, 5});
        System.out.println(change);
    }
}
