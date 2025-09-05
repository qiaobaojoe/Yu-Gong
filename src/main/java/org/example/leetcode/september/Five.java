package org.example.leetcode.september;

import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author qiaobao
 * @since 2025/9/5
 */
public class Five {

    public int numberOfPairs(int[][] points) {
        //  暴力的方法，选择第一个点作为左上角的点(x1,y1)，存在一个点(x2,y2)  x1 <= x2 && y1 >= y2 ，但是还需要判断中间是否存在点
        //  什么条件下这个点会在中间    x1 <= x3 <= x2 && y2 <= y3 <= y1
        Comparator<int[]> comparator = (a, b) -> {
            if (a[0] != b[0]) {
                // x 坐标按照正序排列
                return a[0] - b[0];
            }
            // x相等的情况下，按照y坐标倒序
            return b[1] - a[1];
        };
        Arrays.sort(points, comparator);
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int[] a = points[i];
            for (int j = i + 1; j < n; j++) {
                // 因为是排过序的，所以这里 b 一定是在右边的也是 xb >= xa,所有就是要比较 yb <= ya
                // 并且如果上面的y坐标比较成立，就要退出循环，不需要继续遍历
                int[] b = points[j];
                if (b[1] <= a[1]) {
                    ans++;
                    break;
                }

            }
        }


        return ans;
    }

    public static void main(String[] args) {
        int[][] points = {{1, 5}, {2, 0}, {5, 5}};
        Five five = new Five();
        System.out.println(five.numberOfPairs(points));

    }
}
