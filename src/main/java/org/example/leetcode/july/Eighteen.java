package org.example.leetcode.july;

/**
 * @author qiaobao
 * @since 2025/7/18
 */
public class Eighteen {
    public int maximumLength(int[] nums, int k) {
        // 昨天讨论的情况是k=2
        // 当时情况有三种：1. 全部偶数 2. 全部是奇数 3. 是奇偶交替
        // 当时的求解方法把这三种方案的子序列个数都给枚举出来，比较哪一个最大
        // 但是现在 除以K的余数，余数全部相同就已经有 [0...k] 方案了，再加上及交替的组合的场景 (k* (k-1)) / 2,枚举结果的方法已经没有办法用了
        // 如果转换成最长递增子类似的问题，来思考，我还是能找到一些思路的
        // dp[i] = n 表示按照nums[i]结尾的最长有效子序列长度是 n
        // 这里有点麻烦了，递增的判断只要和前一个元素判断就行了，但是你现在有效的判断是需要连续三个元素才可以判断的
        // 我现在也已经知道 求 余数的推导工时  (a + b) % k = (b + c) % k  ===>   (c - a) % k = 0
        // 这里因为 nums[i]都不是负数，存在两种情况 c == a 或者 c == a + x * k ;这里情况满足的情况下b是多少反而不关注了
        // 枚举余数的方法，我是可以做的，这个时候，就只需要关注前一个元素了
        int n = nums.length;
        if (n == 2) {
            return 2;
        }
        int ans = 2;
        for (int i = 1; i <= k; i++) {
            // 余数  = i   (a + b) % k = i == > b%k  =  i - a%k 这里0的情况代表 i = 5
            int[] dp = new int[n];
            // 以第一个元素结尾，长度为1
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                int res = 1;
                for (int m = 0; m < j; m++) {
                    if (nums[j] % k == (i - nums[m] % k + k) % k) {
                        res = Math.max(dp[m] + 1, res);
                    }
                }
                dp[j] = res;
                ans = Math.max(ans, res);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Eighteen eighteen = new Eighteen();
        int[] nums = {9, 8, 9};
        int k = 5;
        System.out.println(eighteen.maximumLength(nums, k));
    }
}
