package org.example.yugong.leetCode.dp;

/**
 * 求解最长递增子序列
 *
 * @author qiaobao
 * @since 2021-02-05
 */
public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {

        int length = nums.length;

        if(length<2){
            return  length;
        }

        int[] LISlength = new int[length];
        LISlength[length -1] = 1;

        for(int i = length-2;i>=0;i--){
            // 寻找  i~~n-1 区间内比 i大的值
            int nextLISLength = 0;
            for(int j = i+1;j<length;j++){
                if(nums[i]<nums[j]){
                    int tem = LISlength[j];
                    if(tem>nextLISLength){
                        nextLISLength = tem;
                    }
                }
            }
            LISlength[i] = nextLISLength +1;
        }

        int lengthOfLIS = 0;
        for(int i = 0;i<length;i++){
            if(LISlength[i]>lengthOfLIS){
                lengthOfLIS = LISlength[i];
            }
        }

        return lengthOfLIS;


    }
}
