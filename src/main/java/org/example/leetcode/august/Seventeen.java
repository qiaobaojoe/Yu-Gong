package org.example.leetcode.august;

/**
 * @author qiaobao
 * @since 2025/8/17
 */
public class Seventeen {

    public int maxVowels(String s, int k) {
        // i - i+k 的区间内，判断区间内元音字符的数量
        // 暴力的解法是很简单，这个主要是要优化重复判断的问题
        char[] list = s.toCharArray();
        int n = list.length;
        int ans = 0;
        if (n < k) {
            for (char c : list) {
                if (isOriginChar(c)) {
                    ans++;
                }
            }
            return ans;
        }

        for (int i = 0; i + k <= n; i++) {
            // 区间范围【i,i+k-1】范围内的数据
            int cur = 0;
            for (int j = i; j < i + k; j++) {
                if (isOriginChar(list[j])) {
                    cur++;
                }
            }
            ans = Math.max(cur, ans);
            // 每次迭代会讲 i去除，新增 i+k
        }
        return ans;

    }

    private boolean isOriginChar(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        Seventeen seventeen = new Seventeen();
        System.out.println(seventeen.maxVowels("leetcode", 3));
    }
}
