package org.example.leetcode.september;

/**
 * @author qiaobao
 * @since 2025/9/7
 */
public class Six {

    public int minimumRecolors(String blocks, int k) {
        // 这道题目就是求出在大小为k窗口中，白色方块的最小数量
        // 这里找到为0情况就直接退出
        int ans = 0;
        int n = blocks.length();
        char[] list = blocks.toCharArray();
        for (int i = 0; i < n; i++ ){
            char in = list[i];
            if (i < k){
                if (in == 'W'){
                    ans ++;
                }
                continue;
            }
            if(ans == 0){
                return 0;
            }
            int tem = ans;
            char out = list[i - k];
            if (out == 'W'){
                tem --;
            }
            if (in == 'W'){
                tem ++;
            }
            ans = Math.min(ans,tem);
        }
        return ans;

    }

    public static void main(String[] args) {
        Six six = new Six();
        System.out.println(six.minimumRecolors("BWBBWWBBBWBWWWBWWBBWBWBBWBB", 11));
    }
}
