package org.example.leetcode.july;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author qiaobao
 * @since 2025/7/21
 */
public class TwentyOne {
    private Integer[] allSquareList;
    private int[][] memo;

    public int numSquares(int n) {
        // 前面的写法有一个点忽视了，就是这个数必须是完成平方数组成的，脑袋还是混乱，写着写着把这个点忘了，导致解答错误，现在有信心可以做出来
        // 小于当前值的完全平方数列表,这里要注意差一问题，是从 index = 0 代表的是 1的完全平方数
        ArrayList<Integer> allSquareList = new ArrayList<>();

        int i = 1;
        while (true) {
            int square = i * i;
            if (n == square) {
                // 当前n就是完全平方数
                return 1;
            }
            if (square > n) {
                break;
            }
            allSquareList.add(square);
            i++;
        }
        this.memo = new int[allSquareList.size()][n + 1];
        this.allSquareList = allSquareList.toArray(new Integer[0]);


        return dfs(i - 2, n);
    }

    private int dfs(int i, int n) {
        if (i == -1) {
            return 10000;
        }
        if (n == 0) {
            return 0;
        }
        if (memo[i][n] != 0) {
            return memo[i][n];
        }
        int square = allSquareList[i];
        if (n == square) {
            memo[i][n] = 1;
            return 1;
        }
        if (n < square) {
            int ans = dfs(i - 1, n);
            memo[i][n] = ans;
            return ans;

        }
        int ans = Math.min(dfs(i - 1, n), dfs(i, n - square) + 1);
        memo[i][n] = ans;
        return ans;

    }

    public static void main(String[] args) {
        TwentyOne twentyOne = new TwentyOne();
        System.out.println(twentyOne.numSquares(7168));
    }
}
