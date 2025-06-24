package org.example.leetcode.june;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

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

    public int largestArea(String[] grid) {
//        深度搜索的实现，需要对每一个节点都做搜索的起点
//        =0代表当前空间是走廊需要跳过不处理
//        不为0的情况下判断当前空间是否和走廊相邻 x= 0 或则 y= 0 或者上下左右任意方向上为0，或者是否已经被访问过为-1
//        判断上下左右的元素是否和当前的相同，是的，面积增大1，且把元素字符修改为-1，同时节点添加继续判断
//        这个有一个关键问题就是如果和走廊相交，这个节点的问题就不会统计了，所以遍历返回的值
//        y轴的长度
        int m = grid.length;
//        x轴的长度
        int n = grid[0].length();
        if (m == 1 || n == 1) {
            return 0;
        }
//        string不方便字符的替换，这里转换成char数组
        char[][] gridChar = new char[m][];
        for (int i = 0; i < m; i++) {
            gridChar[i] = grid[i].toCharArray();
        }
        int ans = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                char topic = gridChar[y][x];
                int area = dfs(x, y, gridChar, topic);
                ans = Math.max(ans, area);
            }
        }

        return ans;
    }

    private Integer dfs(int x, int y, char[][] grid, char topic) {
        //        y轴的长度
        int m = grid.length;
//        x轴的长度
        int n = grid[0].length;
        if (x >= n || x < 0 || y >= m || y < 0) {
//            发生了数组越界，这里也是靠近走廊了，返回超出范围的负数
            return -500 * 500;
        }
        char curTopic = grid[y][x];
        if (curTopic == '6') {
            return 0;
        }
        if (curTopic == '0') {
//            靠近走廊，不纳入最大面积统计，这里返回一个超出范围的负数，让ans不会更新
            return -500 * 500;
        }
        if (curTopic == topic) {
//            主题一致面积加1
            int area = 1;
//            这里要标记为已返回
            grid[y][x] = '6';
//            上下左右四个方向都需要处理
            area += dfs(x - 1, y, grid, topic);
            area += dfs(x + 1, y, grid, topic);
            area += dfs(x, y - 1, grid, topic);
            area += dfs(x, y + 1, grid, topic);
            return area;
        }
//      其他主题区域，面积不会正价
        return 0;
    }

    public int[][] highestPeak(int[][] isWater) {
//      找到给出的矩阵里面所有的水域坐标  isWater[i][j] == 1 作为跟节点
//        将根节点上下左右添加上带访问队列中
//        处理节点 如果节点已经访问过 或则是 跟节点 不处理  否则  这个点面积就是  high + 1 （high是上次相邻的单元高度）
//        并且将 当前将相关单元和当前高度，添加到下一次遍历
//        y方向长度
        int m = isWater.length;
//        x方向长度
        int n = isWater[0].length;
        if (m == 1 && n == 1) {
            return new int[][]{{0}};
        }
        int[][] ans = new int[m][n];
        LinkedList<Coordinate> waitList = new LinkedList<>();
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (isWater[y][x] == 1) {
//                    ans初始值已经为0，这里水域面积不用赋值，但是需要再原地图标记已访问过
                    isWater[y][x] = -1;
                    waitList.add(new Coordinate(x - 1, y, 0));
                    waitList.add(new Coordinate(x + 1, y, 0));
                    waitList.add(new Coordinate(x, y - 1, 0));
                    waitList.add(new Coordinate(x, y + 1, 0));
                }
            }
        }
        while (!waitList.isEmpty()) {
            Coordinate cur = waitList.remove();
//            数组越界不处理
            if (cur.x < 0 || cur.y < 0 || cur.x >= n || cur.y >= m) {
                continue;
            }
//            已经访问过不处理
            if (isWater[cur.y][cur.x] == -1) {
                continue;
            }
//            剩余的结果就是没有计算面积的陆地
            int high = cur.high + 1;
            ans[cur.y][cur.x] = high;
//            原地图标记访问
            isWater[cur.y][cur.x] = -1;
//            其他方向添加队列
            waitList.add(new Coordinate(cur.x - 1, cur.y, high));
            waitList.add(new Coordinate(cur.x + 1, cur.y, high));
            waitList.add(new Coordinate(cur.x, cur.y - 1, high));
            waitList.add(new Coordinate(cur.x, cur.y + 1, high));

        }
        return ans;
    }

    static class Coordinate {
        int x;
        int y;
        int high;

        public Coordinate(int x, int y, int high) {
            this.x = x;
            this.y = y;
            this.high = high;
        }
    }

    public static void main(String[] args) {
        TwentyTwo twentyTwo = new TwentyTwo();
        System.out.println(twentyTwo.highestPeak(new int[][]{{0, 0, 1}, {1, 0, 0}, {0, 0, 0}}));

    }
}
