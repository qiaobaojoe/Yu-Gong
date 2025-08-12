package org.example.leetcode.august;

import java.util.Arrays;

/**
 * @author qiaobao
 * @since 2025/8/7
 */
public class Seven {
    private int[] costs;
    private int[] isTravelList;
    private int lastDay;
    private int[] memo;

    public int mincostTickets(int[] days, int[] costs) {
        // 这里迭代的方法我很难相处来，我应该怎样设计这个动态方程


        return 1;

    }

    // 我理解了题解里面从右向左递归的方式，我这里尝试改变一下，从左向右递归
    // 这里的子问题就是 i --- 最后一天的最小开销
    private int dfs(int i) {
        if (i > lastDay) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int res = 0;
        // 如果当前不需要旅行，就不用买票
        if (isTravelList[i] == 0) {
            res = dfs(i + 1);
            memo[i] = res;
            return res;
        }

        // 买一天的票和七天的票比较
        res = Math.min(dfs(i + 1) + costs[0], dfs(i + 7) + costs[1]);
        res = Math.min(res, dfs(i + 30) + costs[2]);
        memo[i] = res;
        return res;
    }

    public static void main(String[] args) {
        Seven seven = new Seven();
        System.out.println(seven.mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
    }
}
