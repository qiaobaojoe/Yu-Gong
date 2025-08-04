package org.example.leetcode.august;

/**
 * @author qiaobao
 * @since 2025/8/4
 */
public class Four {
    private int[] encode;
    private int[] memo;

    public int numDecodings(String s) {
        // 这个子问题是很容易找到的，将字符串转化成数组
        char[] varList = s.toCharArray();
        int n = varList.length;
        int[] encode = new int[n];
        int[] memo = new int[n];
        for (int i = 0; i < n; i++) {
            encode[i] = (int) varList[i] - 48;
            memo[i] = -1;
        }
        this.encode = encode;
        this.memo = memo;
        // 转换成子问题，字母的数字编码只有可能是 1 位或者 2 位不能以 0 开头的整数，通过这一特性
        // s 按照 0 开头，那就没有合法的编码
        // 当前字符 > 2 或者 下一个字符 >6,只能选择当前的字符 dfs(n) = dfs(n-1)
        // 其他的情况 dfs(n) = dfs(n-1) +dfs(n-2)


        return dfs(0);
    }

    private int dfs(int i) {
        if (memo[i] != -1) {
            return memo[i];
        }
        int n = encode.length;
        if (encode[i] == 0) {
            memo[i] = 0;
            return 0;
        }
        if (i == n - 1) {
            memo[i] = 1;
            return 1;
        }
        // 计算两位数的值
        int twoDigit = encode[i] * 10 + encode[i + 1];
        if (i == n - 2) {
            // 看一下最后两位
            if (encode[n - 1] == 0 && encode[n - 2] > 2) {
                return 0;
            }
            // 0 <= twoDigit 26 这个时候有两种解法，但是 10 和 20的情况需要特殊处理只有一种
            if (twoDigit == 10 || twoDigit == 20 || twoDigit > 26) {
                memo[i] = 1;
                return 1;
            }
            memo[i] = 2;
            return 2;
        }
        int res;
        if (twoDigit > 26) {
            res = dfs(i + 1);
            memo[i] = res;
            return res;
        }
        res = dfs(i + 1) + dfs(i + 2);
        memo[i] = res;
        return res;

    }

    public static void main(String[] args) {
        Four four = new Four();
        System.out.println(four.numDecodings("2611055971756562"));
    }
}
