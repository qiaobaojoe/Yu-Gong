package org.example.leetcode.july;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author qiaobao
 * @since 2025/7/21
 */
public class TwentyOne {
    public int numSquares(int n) {
        // 前面的写法有一个点忽视了，就是这个数必须是完成平方数组成的，脑袋还是混乱，写着写着把这个点忘了，导致解答错误，现在有信心可以做出来
        // 小于当前值的完全平方数列表,这里要注意差一问题，是从 index = 0 代表的是 1的完全平方数
        // 再整理一下，重新写，利用一下缓存的局部性原理，避免随机访问
        int l = 0;
        ArrayList<Integer> quareList = new ArrayList<>();
        while (true){
            int quare = (l+1) * (l+1);
            if (quare > n){
                break;
            }
            if (quare == n){
                // 当前数字是完全平方数，直接返回
                return 1;
            }
            quareList.add(quare);
            l++;
        }
        Integer[] allQuares = quareList.toArray(new Integer[0]);
        int[][] dp = new int[l][n+1];
        for (int[] row:dp){
            Arrays.fill(row,100000);
        }
        for(int i = 1; i <= n ; i++){
            dp[0][i] = i;
        }

        for (int j = 1 ; j < l; j++){
            for (int i = 1; i <= n ; i++){
                if (i == allQuares[j]){
                    dp[j][i] = 1;
                    continue;
                }
                if (i < allQuares[j]){
                    dp[j][i]  = dp[j-1][i];
                    continue;
                }
                int res = dp[j][i-allQuares[j]] + 1;
                res = Math.min(res,dp[j-1][i]);
                dp[j][i] = res;
            }
        }


        return dp[l-1][n];
    }

    public static void main(String[] args) {
        TwentyOne twentyOne = new TwentyOne();
        System.out.println(twentyOne.numSquares(12));
    }
}
