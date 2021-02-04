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

    /**
     * 不能连续跳过两个台阶
     * 思考了一下，有两种方法：
     * 1、在计算完成后做减枝，就是算出连续跳两次的方案有多少种
     * 2、就是穷举出一个固定的序列
     */
    public int climbingStairsLimit(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        boolean flag = false;

        for (int i = 3; i <= n; i++) {

            if (flag) {
                dp[i] = dp[i - 1];
                flag = false;
            }else {
                dp[i] = dp[i - 1] + dp[i - 2];
                flag = true;
            }
        }
        return dp[n];
    }

    public int climbingStairsLimit2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        boolean flag = false;

        for (int i = 3; i <= n; i++) {

            dp[i] = dp[i - 1] + dp[i - 3];
        }
        return dp[n];
    }


    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbingStairs(10));

        System.out.println(climbingStairs.climbingStairsLimit(10));
        System.out.println(climbingStairs.climbingStairsLimit2(10));

    }





}

