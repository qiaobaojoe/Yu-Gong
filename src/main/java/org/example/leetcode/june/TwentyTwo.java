package org.example.leetcode.june;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

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
        for (int i = 1; i < n; i++) {
            int longestI = 1;
            int[] pairI = pairs[i];
            for (int j = 0; j < i; j++) {
                int[] pairJ = pairs[j];
                if (pairI[0] > pairJ[1]) {
                    longestI = Integer.max(longestI, dp[j] + 1);
                }
            }
            dp[i] = longestI;
        }
        return Arrays.stream(dp).max().orElse(0);


    }

    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        if (n == 1) {
            return 1;
        }
        // 单个元素也可以组成一个子序列
        // 下一个值就必须要 == i+k
        // 这里首先是暴力的解法，数组中的每一个元素都可以作为等差子序列的起点或者终点，遍历每一个选择的结果 0(n^2/2)，当然这里或许可以采用记忆集的方式优化
        // 想一想有没有推导的方式，我觉得是类似最长子序列的
        HashMap<Integer, Integer> dp = new HashMap<Integer, Integer>(n);
        int ans = 1;
        for (int a : arr) {
            int longestA = dp.getOrDefault(a - difference, 0) + 1;
            ans = Math.max(ans, longestA);
        }
        return ans;
    }

    public static void main(String[] args) {
        TwentyTwo twentyTwo = new TwentyTwo();
        twentyTwo.findLongestChain(new int[][]{
                {1, 2}, {2, 3}, {3, 4}
        });
    }
}
