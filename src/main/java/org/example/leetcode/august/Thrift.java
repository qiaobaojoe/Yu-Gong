package org.example.leetcode.august;

/**
 * @author qiaobao
 * @since 2025/8/13
 */
public class Thrift {

    private int x;

    public int numberOfWays(int n, int x) {
//      看了一眼提示，给出了动态规划的提示，这出乎我的预料，我也对这个题目高看一下，简洁的题意，揭示了通用的问题，很优秀的题目
//      但是这个子问题是什么暂时没有想出来，从n 还是 x 下手？
//      明白了，这个就是硬币组合题目，但是一个硬币只能使用一次
//      把所有小于 n 的 整数 x次幂算出来，其实只需要求出这个数字的下标就可以
        int i = 1;
        int pow = 1;
        while (pow <= n) {
            i++;
            pow = (int) Math.pow(i, x);
        }
        this.x = x;

        return dfs(n, i - 1);
    }

    private int dfs(int n, int i) {
        if (n == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        // 这里数据的顺序不同，要视为相同的组合，所以这里只存在选择 i 和选择 i 的下一个两个情况
        int pow = (int) Math.pow(i, x);

        if (pow > n) {
            // 这个时候只能选择下一个
            return dfs(n, i - 1);
        }
        // 这里不能选择重复的硬币，所有选择 i 之后就要选择下一个
        return dfs(n - pow, i - 1) + dfs(n, i - 1);
    }

    public static void main(String[] args) {
        Thrift thrift = new Thrift();
        System.out.println(thrift.numberOfWays(10, 2));
        System.out.println(thrift.numberOfWays(4, 1));
    }
}
