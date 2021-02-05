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
     *
     * 前面的两种思路都是有问题的，实际上还是暴力的破解法
     * 实际上对于动态规划而言最终要的就是推导出公式
     * 上面的状态转移公式：
     * f(n) = f(n-1) + f(n-2)
     * 对于不能连续跳两次
     * f(n) = f(n-1) + g(n-2)
     * 所以对于目前而言最重要的就是把g(n) 给表示出来
     *
     */
    public int climbingStairsLimit(int n) {
        if (n <= 3) {
            return n;
        }
        int[][] dp = new int[n + 1][3];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;


        for (int i = 3; i <= n; i++) {
            dp[i][1] = dp[i - 1][1] + dp[i - 1][2];
            dp[i][2] = dp[i - 2][1];
        }
        return dp[n][1] + dp[n][2];
    }

    public int climbingStairsLimit2(int n) {
        if (n <= 3) {
            return n;
        }

        // first total methods number
        // second last step is one step methods number
        int[][] dp = new int[n][2];

        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[1][0] = 2;
        dp[1][1] = 1;
        dp[2][0] = 3;
        dp[2][1] = 2;

        for (int i = 3; i < n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][1];
            dp[i][1] = dp[i-1][0];
        }
        return dp[dp.length-1][0];
    }


    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbingStairs(10));

        System.out.println(climbingStairs.climbingStairsLimit(10));
        System.out.println(climbingStairs.climbingStairsLimit2(10));

    }





}

