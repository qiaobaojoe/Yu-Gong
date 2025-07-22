package org.example.leetcode.july;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author qiaobao
 * @since 2025/7/21
 */
public class TwentyOne {
    private ArrayList<Integer> allSquareList;

    public int numSquares(int n) {
        // 前面的写法有一个点忽视了，就是这个数必须是完成平方数组成的，脑袋还是混乱，写着写着把这个点忘了，导致解答错误，现在有信心可以做出来
        // 小于当前值的完全平方数列表
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
        this.allSquareList = allSquareList;

        return dfs(n);
    }

    private int dfs(int n) {
        int size = allSquareList.size();
        int ans = n;
        for (int i = size - 1; i >= 0; i--) {
            int square = allSquareList.get(i);
            if (square > n) {
                continue;
            }
            if (square == n) {
                return 1;
            }
            ans = Math.min(ans, dfs(n - square) + 1);
        }
        return ans;

    }

    public static void main(String[] args) {
        TwentyOne twentyOne = new TwentyOne();
        System.out.println(twentyOne.numSquares(67));
    }
}
