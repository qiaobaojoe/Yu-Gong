package org.example.leetcode.august;

/**
 * @author qiaobao
 * @since 2025/8/25
 */
public class TwentyFive {

    private int m;
    private int n;

    public int[] findDiagonalOrder(int[][] mat) {
        // 非常好的题目，很简单的题意，也需要技巧才能完成
        // 首先可以确认遍历的起点就是(0,0) 这个时候方向是向右上方下一个点的位置是 (x + 1 ,y - 1)
        // 但是 x-1 < 0 越界了，这个时候，起点变成(1,0)，并且调转方向为左下(x - 1, y + 1) 下一个点为(0,1)
        // 在下一个点是(-1,2) 这个时候又发生越界 (0,2) ,并调转方向

        // 通过上面的分析可以确认，算法的核心，就是确认边界和确认方向
        // x轴，也就是水平方向的数组长度
        this.m = mat[0].length;
        // y轴，也就是垂直方向的数组长度
        this.n = mat.length;
        int[] ans = new int[m * n];
        // 起始点的坐标
        int x = 0;
        int y = 0;
        int dx = 1;
        int dy = -1;
        for (int i = 0; i < m * n; i++) {
            ans[i] = mat[y][x];
            x += dx;
            y += dy;
            System.out.println("x=" + x + " y=" + y);
            if (isOutBound(x, y)) {
                // 调整方向
                dx = -dx;
                dy = -dy;
                // 下一个起点的计算方式，向右下方平移
                x += 1;
                y += 1;
                while (isOutBound(x, y)) {
                    x += dx;
                    y += dy;
                }
            }
        }

        return ans;
    }

    private boolean isOutBound(int x, int y) {
        return x < 0 || y < 0 || x == m || y == n;
    }

    public static void main(String[] args) {
        TwentyFive twentyFive = new TwentyFive();
        int[] ans = twentyFive.findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        for (int i : ans) {
            System.out.println(i);
        }
    }
}
