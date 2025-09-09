package org.example.leetcode.september;

import java.util.Arrays;

/**
 * @author qiaobao
 * @since 2025/9/9
 */
public class Nine {

    private static Integer MOD = 1_000_000_000 + 7;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // 使用动态规划，也就是迭代的方式实现
        // 这里的状态方程代表的是每天新增的知道秘密的人数
        int[] dp = new int[n + 1];
        // 第一天的时候只有一个人知道
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i <= delay) {
                // 这个时候还不能分享秘密
                continue;
            } else {
                int shareCount = 0;
                for (int j = i - forget + 1; j <= i - delay; j++) {
                    if (j <= 0) {
                        continue;
                    }
                    shareCount += dp[j];
                    shareCount %= MOD;
                }
                dp[i] = shareCount;
            }
        }


        // 第n天知道的人总数 noForgetDay =  n-forget+1；从noForgetDay开始这些天新增知道秘密的人数的和
        int noForgetDay = n - forget + 1;
        int ans = 0;
        for (int k = noForgetDay; k <= n; k++) {
            ans += dp[k];
            ans %= MOD;
        }
        return ans;
    }

}
