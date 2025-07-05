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

    public int[][] getMemo() {
        return memo;
    }

    public void setMemo(int[][] memo) {
        this.memo = memo;
    }

    //    利用缓存的数组遍历
    public void hitCache() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = 1;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("hitCache time = " + (end - start));
    }
//    未利用缓存的数组遍历
    public void missCache() {
        long start = System.currentTimeMillis();
        for (int j = 0; j < memo[0].length; j++) {
            for (int i = 0; i < memo.length; i++) {
                memo[i][j] = 1;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("missCache time = " + (end - start));
    }

    public static void main(String[] args) {

        MaxUncrossedLinesSolution solution = new MaxUncrossedLinesSolution();
        solution.setMemo(new int[20000][10000]);
//        统计两个方法的耗时
        solution.hitCache();
        solution.missCache();

    }

}
