package org.example.yugong.leetCode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nus = new int[]{3,2,1,6,0,5};
        solution.constructMaximumBinaryTree(nus);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = new TreeNode();
        if(nums == null || nums.length == 0){
            return root;
        }

        return helpConstruct(nums,0,nums.length -1);


    }

    public TreeNode helpConstruct(int[] nums,int left ,int right) {
        // 递归的停止条件
        TreeNode root = new TreeNode();
        int maxIndex = findMaxNumIndex(nums,left,right);
        if(maxIndex == -1){
            return root;
        }
        root.val = nums[maxIndex];
        root.left = helpConstruct(nums,left,maxIndex-1);
        root.right = helpConstruct(nums,maxIndex+1,right);


        return root;

    }


    //  找到最大值
    public int findMaxNumIndex(int[] nums,int left ,int right){
        if(left == right){
            return left;
        }

        if(left > nums.length -1 ||right <  0 ){
            return -1;
        }

        if(left > right){
            return -1;
        }
        int index=left;
        while (left < right){
            left++;
            if(nums[index] < nums[left] ){
                index = left;
            }
        }
        return index;
    }

     public class TreeNode {
    int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
     }
  }
}