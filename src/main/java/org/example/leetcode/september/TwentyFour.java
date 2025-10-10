package org.example.leetcode.september;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author qiaobao
 * @since 2025/9/24
 */
public class TwentyFour {
    public int[] dailyTemperatures(int[] temperatures) {
        // 暴力算法，从对当前值向后遍历，找到最大的  算法复杂段最坏的情况   n + n-1 +.... 1 n ** 2
        // 这里尝试用单点栈的方法，完成这道题，首先是从右向左的入栈和出栈操作
        // 单调栈里面保存的是什么数据：站在左边向右边仰望的视角，这里在谷底都看不到了，这保存的只有从当前位置i，往右边仰望的山峰
        // 何时入栈：栈中数据为空时，我自己的就是山峰，这个时候要添加入栈，而且没有比我大的，结果为0
        //          栈中的数据不为空，这个时候我的数据比栈中第一个数据小，直接入栈，结果是 first() - 当前
        // 何时出栈：栈中数据不为空，当前数据比第一个数据小，要让第一个数据先出栈，在比较下一个，直到找到比当前数据大的，或者栈为空的时候
        int n = temperatures.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int curTem = temperatures[i];
            if (stack.isEmpty()) {
                stack.push(i);
                ans[i] = 0;
                continue;
            }

            if (curTem < temperatures[stack.peek()]) {
                ans[i] = stack.peek() - i;
                stack.push(i);
                continue;
            }
            // 这个时候是 大于或等于 的情况，需要先出栈了
            while (!stack.isEmpty() &&temperatures[stack.peek()] <= curTem) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = 0;
                stack.push(i);
                continue;
            }
            ans[i] = stack.peek() - i;
            stack.push(i);


        }
        return ans;
    }

    public static void main(String[] args) {
        TwentyFour twentyFour = new TwentyFour();
        int[] ans = twentyFour.dailyTemperatures(new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70});
        for (int i : ans) {
            System.out.println(i);
        }
    }
}
