package org.example.yugong.leetCode.tree;


import java.util.HashMap;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class RebuildTree {
    public static void main(String[] args) {

    }

    public HashMap<Integer,Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder == null || preorder.length ==0 ){
            return null;
        }
        int preInx = 0;
        int cur = preorder[preInx];
        TreeNode root = new TreeNode(cur);
        int inInx = findInorderIndex(cur,inorder);
//        这里代表的是   inInx - inLeft
        int leftLength = inInx- 0 ;
        if (leftLength == 0) {
            root.left = null;
        }else {
            root.left = helpBuildTree(preorder, preInx + 1, preInx + leftLength,
                    inorder, 0, inInx - 1);
        }

        int rightLength = inorder.length - 1 - inInx;
        if (rightLength == 0) {
            root.right = null;
        }else {
            root.right = helpBuildTree(preorder, preInx + leftLength + 1, preorder.length - 1,
                    inorder, inInx + 1, preorder.length - 1);
        }

        return root;
    }

    private TreeNode helpBuildTree(int[] preorder, int preLeft, int preRight,
                                   int[] inorder, int inLeft, int inRight){

        TreeNode root = new TreeNode(preorder[preLeft]);
        int inorderIndex = findInorderIndex(preorder[preLeft], inorder);
        int leftLength = inorderIndex - inLeft;
        if (leftLength == 0) {
            root.left = null;
        }else {
            root.left = helpBuildTree(preorder, preLeft + 1, preLeft + leftLength,
                    inorder, inLeft, inorderIndex - 1);
        }

        int rightLength = inRight - inorderIndex;
        if (rightLength == 0) {
            root.right = null;
        }else {
            root.right = helpBuildTree(preorder, preLeft + leftLength + 1, preRight,
                    inorder, inorderIndex + 1, inRight);
        }
        return root;

    }

    private int findInorderIndex(int cur, int[] inorder) {
        if (inorderMap == null) {
            inorderMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderMap.put(inorder[i], i);
            }
        }
        return inorderMap.get(cur);
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}