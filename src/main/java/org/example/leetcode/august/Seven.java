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
        // 这里首先要把 costs 的几种情况确认了
        // costs[0] * 7 > costs[1] 这个时候连续旅游天数较多的时候，就要考虑买 7 天的联票
        // 但是多少天连续的时候，买联票呢
        // 对于月票也是一样的问题，超过多少天购买下一等级的联票很难整理出来
        this.costs = costs;
        this.lastDay = days[days.length - 1];
        int[] isTravelList = new int[lastDay + 1];
        for (int d : days) {
            isTravelList[d] = 1;
        }
        this.isTravelList = isTravelList;
        int[] memo = new int[lastDay + 1];
        Arrays.fill(memo, -1);
        this.memo = memo;
        return dfs(1);

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
}
