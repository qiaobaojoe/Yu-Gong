package org.example.leetcode.june;

import java.util.Arrays;

/**
 * @author qiaobao
 * @since 2025/7/4
 */
public class MaxUncrossedLinesSolution {

    private int[][] memo;

    private int[] nums1;

    private int[] nums2;

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        this.nums1 = nums1;
        this.nums2 = nums2;
        return dfs(m - 1, n - 1);

    }

    private int dfs(int i, int j) {
//        判断递归的终止条件
        if (i < 0 || j < 0) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res;
        if (nums1[i] == nums2[j]) {
            res = dfs(i - 1, j - 1) + 1;
            memo[i][j] = res;
            return res;
        }
        res = Math.max(dfs(i - 1, j), dfs(i, j - 1));
        memo[i][j] = res;
        return res;
    }

}
