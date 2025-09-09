package org.example.leetcode.september;

import java.util.Arrays;

/**
 * @author qiaobao
 * @since 2025/9/9
 */
public class Nine {

    private static Integer MOD = 1_000_000_000 + 7;
    private int delay;
    private int forget;
    private int[] memo;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // 最直接的想法，设计一个结构体出来  知道的时间 t,两个方法，一个是判断是否到延迟时间，生成一个新的结构体
        // 还有一个方法是否判断是否遗忘，到了的话从知道的人列表中把这个结构体剔除
        // 这里结构体最关键的元素就是知道这个秘密的天数

        // 第一天，只有一个人知道秘密 这个人知道秘密的天数是1
        // 延迟了 delay 天后才有其他人知道
        this.delay = delay;
        this.forget = forget;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        this.memo = memo;
        // n-delay天前知道秘密的人，n-forge之后还没有忘记，还会继续分享秘密
        int noForgetDay = n - forget + 1;

        int shareDay = n - delay;

        int ans = 0;
        for (int d = noForgetDay; d < n; d++) {
            if (d <= shareDay) {
                ans += (dfs(d) * 2) % MOD;
            } else {
                ans += dfs(d);
            }
            ans %= MOD;
        }

        return ans;
    }

    // 我可以想到这个递归函数关键是这个递归函数的返回值怎么设计
    // 这里返回值是 在 第n天的时候后新增了多少人知道秘密
    public int dfs(int n) {
        if (n < 1) {
            return 0;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        if (n == 1) {
            memo[n] = 1;
            return 1;
        }
        int noForgetDay = n - forget + 1;

        int shareDay = n - delay;

        int ans = 0;
        for (int d = noForgetDay; d < n; d++) {
            if (d <= shareDay) {
                ans += dfs(d);
                ans %= MOD;
            }
        }
        ans %= MOD;
        memo[n] = ans;
        return ans;

    }
}
