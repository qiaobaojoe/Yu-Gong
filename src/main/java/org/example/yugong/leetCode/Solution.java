package org.example.yugong.leetCode;

/**
 * @author qiaobao
 * @since 2021-02-21
 */
class Solution {
    public int search(int[] nums, int target) {
        if(nums==null || nums.length ==0){
            return 0;
        }
        int length = nums.length;
        int total = 0;
        if(length<3){
            for(int i = 0;i<length;i++){
                if(nums[i] == target){
                    total++;
                }
            }
            return total;
        }
        int index = findIndex(nums,target);
        if(index == -1){
            return total;
        }
        return searchAround(nums,index);



    }


    private int findIndex(int[] nums,int target){
        int left = 0;
        int right = nums.length -1;


        while(left !=right){
            int mid = (right + left )/2;
            int cur = nums[mid];
            if(cur < target){
                left = mid +1;
                continue;
            }
            if(cur == target){
                return mid;
            }
            right = mid -1;
        }


        return -1;




    }

    private int searchAround(int[] nums, int index){
        int total = 1;
        int target = nums[index];

        int left  = index ;
        while(left>0){
            left--;
            if(nums[left] == target){
                total++;
            }else{
                break;
            }
        }

        int right = index;

        while(right < nums.length -1){
            right++;
            if(nums[right] == target){
                total++;
            }else{
                break;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int search = solution.search(new int[]{-99999,-99998,-9999,-999,-99,-9,-1}, 0);
        System.out.println(search);
    }

}
