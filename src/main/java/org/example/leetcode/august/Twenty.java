package org.example.leetcode.august;

/**
 * @author qiaobao
 * @since 2025/8/20
 */
public class Twenty {
    private int[][] matrix;
    // X 方向的长度
    private int m;
    // y 方向的长度
    private int n;

    public int countSquares(int[][] matrix) {
        // 不错的题目，从设计上来说和昨天的题目很相似，如果确认最大的正方形矩阵，就可以得到下一个级别的矩阵数量
        // 但是这里矩阵判断比之前线性列表要麻烦多了
        // 这里暴力的方式，是很直接的所有可能size 的矩阵
        this.matrix = matrix;
        this.m = matrix[0].length;
        this.n = matrix.length;
        int ans = 0;
        for (int i = Math.min(m, n); i > 0; i--) {
            // 确定需要遍历的左上角的位置
            for (int x = 0; x <= m - i; x++) {
                for (int y = 0; y <= n - i; y++) {
                    ans += isSquare(x, y, i);
                }
            }
        }

        return ans;
    }

    private int isSquare(int x, int y, int len) {
        System.out.println("x = " + x + ", y = " + y + ", len = " + len);
        for (int j = y; j < y + len; j++) {
            for (int k = x; k < x + len; k++) {
                if (matrix[j][k] == 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Twenty twenty = new Twenty();
        int[][] matrix = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        int i = twenty.countSquares(matrix);
        System.out.println(i);
    }
}
