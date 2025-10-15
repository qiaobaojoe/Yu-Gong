package org.example.leetcode.october;

import java.util.List;

/**
 * @author qiaobao
 * @since 2025/10/15
 */
public class Fifteen {

    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        // 遍历这个数组，找出所有的连续递增子数组，
        // 这里需要保证是连续相邻区间
        // 我认为需要两次遍历，首先找到所有递增子数组长度 = k的子数组
        // 再判断子数组是否存在相邻情况
        int n = nums.size();
        // 是否存在长度等于 k 的子数组
        boolean[] bitMap = new boolean[n];
        // 求当前的连续子数组长度
        for(int i = 0 ; i < n - k ; i++){
            int compareNum = nums.get(i);
            boolean isIncreasing = true;
            for(int j = 1;j < k;j++){
                if (nums.get(i+j) > compareNum){
                    compareNum = nums.get(i+j);
                }else{
                    isIncreasing = false;
                    break;
                }
            }
            bitMap[i] = isIncreasing;
            if(isIncreasing){
                if(i >= k ){
                    if (bitMap[i-k]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
