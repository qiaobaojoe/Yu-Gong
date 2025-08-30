package org.example.leetcode.august;

/**
 * @author qiaobao
 * @since 2025/8/29
 */
public class TwentyNine {


    public long flowerGame(int n, int m) {
        // 环形的数据结构，这里是行动结束后所有的鲜花都被摘完了，当前对手赢取胜利
        // 这里我认为和胜负关键就是  x + y 的总数 total
        // alice先行动，Alice获取胜利，所有total一定要是奇数
        // 到这里题目就简化了 但是我简化的方式不对
        // total是奇数：x是偶数y是奇数 或者 x是奇数y是偶数
        // 在[1,n]的范围内 x偶数的个数 在[1,m]的范围内 y奇数的个数
        long ans = (long) n / 2 * (m - (long) m/2);
        ans += (long) (n - n/2) * (long) m/2;
        return ans ;
    }

    public static void main(String[] args) {
        TwentyNine twentyNine = new TwentyNine();
        System.out.println(twentyNine.flowerGame(4, 3));
    }
}
