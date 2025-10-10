package org.example.leetcode.october;

/**
 * @author qiaobao
 * @since 2025/10/9
 */
public class Nine {
    public long minTime(int[] skill, int[] mana) {
        // 理解题意，可以算出酿造一个药水所需要的时间
        // 难点是在于第二个药水何时可以开始酿造
        //      1. 如果酿造第二个药水的法力值 mana[j] >= mana[j-1] 在一个巫师完成后，既可以开始下一次酿造
        //      2. mana[j] < mana[j-1] 情况就比较复杂了，不能和巫师处理的时间段产生重合
        long ans =  0L;
        int n = skill.length;
        int m = mana.length;
        long[] finishedTime = new long[n];
        // 统计第一轮的处理时间
        for(int i = 0 ; i < n ; i++){
            ans += ((long) skill[i] * mana[0]);
            finishedTime[i] = ans;
        }
        for (int j = 1; j < m; j++){
            long startTime= 0L;
            if(mana[j] < mana[j-1]){
                // 这种情况需要考虑，不能和上一轮重合
            }else{
                startTime = finishedTime[0] + 1;
            }
            for(int i = 0 ; i < n ; i++){
                startTime += ((long) skill[i] * mana[j]);
                finishedTime[i] = startTime;
            }
            ans = startTime;

        }
        return ans;

    }

    public static void main(String[] args) {
        Nine nine = new Nine();
        System.out.println(nine.minTime(new int[]{1,1,1}, new int[]{1,1,1}));
    }
}
