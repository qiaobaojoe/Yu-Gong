package org.example.leetcode.june;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author qiaobao
 * @since 2025/6/22
 */
public class TwentyTwo {

    public int findLongestChain(int[][] pairs) {
        // 这里首先我的第一个观点，就是认为要对pairs数对数组按照第一个元素进行排序
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int n = pairs.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i <n; i++) {
            int longestI = 1;
            int[] pairI = pairs[i];
            for (int j = 0; j < i; j++) {
                int[] pairJ = pairs[j];
                if (pairI[0] > pairJ[1]){
                    longestI = Integer.max(longestI, dp[j] + 1);
                }

            }
            dp[i] = longestI;
        }
//        打印dp数组
        for (int i : dp) {
            System.out.println(i);
        }
        return Arrays.stream(dp).max().orElse(0);


    }
    public static void main(String[] args) {
        TwentyTwo twentyTwo = new TwentyTwo();
        twentyTwo.findLongestChain(new int[][]{
                {1, 2}, {2, 3}, {3, 4}
        });
    }
}
