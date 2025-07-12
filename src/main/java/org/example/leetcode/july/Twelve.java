package org.example.leetcode.july;

import java.util.Arrays;

/**
 * @author qiaobao
 * @since 2025/7/12
 */
public class Twelve {

    private int[] prices;

    private int[][] memo;

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1) {
            return 0;
        }
        //1.今天是否可以买股票：a.持有股票中不可以买入 b.冷冻期不可以买入
        //2.今天是否要买股票： a.以后股票会涨，后续日期存在股票价格高于今天价格
        // 如果没有冷冻期，这里就是找价格数组中的递增或递减区间，在递减区间的最值买入，递增的最大值卖出
        // 冷冻期确实会比较麻烦，这里主要是卖出的节点，会有一个机会成本，你可以选择提前1天卖出
        // 第n天是必须不能持有股票的：
        // 1.n-2天的时候已经把股票卖出了
        // 2.n-1天的时候依然持有股票今天选择卖出
        this.prices = prices;
        int[][] memo = new int[n][2];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        this.memo = memo;
        return dfs(n - 1, 0);
    }

    private int dfs(int day, int hold) {
        if (memo[day][hold] != Integer.MIN_VALUE) {
            return memo[day][hold];
        }

        if (day == 0) {
            if (hold == 0) {
                memo[day][hold] = 0;
                return 0;
            }
            // 关键就是 持有的时候要怎么返回值
            // 最后卖出的时候结算利润 profit = p[n] - (买入时的价格) + 买入时的利润
            // 所以这里返回值为  买入时的利润 - 买入的价格
            memo[day][hold] = -prices[0];
            return -prices[0];
        }
        if (day == 1) {
            int res;
            if (hold == 0) {
                // 这里有两种情况，1.是今天卖出 2.是昨天没买入 要比较一下
                res = Math.max(0, dfs(0, 1) + prices[1]);
                memo[day][hold] = res;
                return res;
            }
            // 这里的情况可以是今天买入，也可以是昨天就已买入，今天持有
            res = Math.max(dfs(0, 0) - prices[1], dfs(0, 1));
            memo[day][hold] = res;
            return res;
        }
        // 现在都是通用的场景了
        int res;
        if (hold == 0) {
            // 今天不持有股票：1.在昨天卖出了 2.在前天卖出了 3.在今天卖出了
            int beforeSale = Math.max(dfs(day - 2, 0), dfs(day - 1, 0));
            int todaySale = dfs(day - 1, 1) + prices[day];
            res = Math.max(beforeSale, todaySale);
            memo[day][hold] = res;
            return res;
        }
        // 今天持有股票：1.今天买入的(两天前就要卖出) 2.之前就已经买入了
        int todayBuy = dfs(day - 2, 0) - prices[day];
        res = Math.max(todayBuy, dfs(day - 1, 1));
        memo[day][hold] = res;
        return res;
    }
}
