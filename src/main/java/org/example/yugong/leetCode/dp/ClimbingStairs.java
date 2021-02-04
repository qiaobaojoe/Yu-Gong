package org.example.yugong.leetCode.dp;

/**
 * @author qiaobao
 * @since 2021-02-04
 */
public class ClimbingStairs {

    public int climbingStairs(int n) {
        int[] dp = new int[3];
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 3; i <= n; i++) {
            dp[2] = dp[1] + dp[0];
            dp[0] = dp[1];
            dp[1] = dp[2];
        }
        return dp[2];

    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbingStairs(5));

    }





}

