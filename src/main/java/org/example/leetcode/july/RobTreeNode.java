package org.example.leetcode.july;

import java.util.HashMap;

/**
 * @author qiaobao
 * @since 2025/7/20
 */
public class RobTreeNode {

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

    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return internalRob(root, memo);
    }

    private int internalRob(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        // 当前节点抢劫
        int robMoney = root.val;
        // 孙子节点也要被抢劫
        if (root.left != null) {
            robMoney += internalRob(root.left.left, memo);
            robMoney += internalRob(root.left.right, memo);
        }
        if (root.right != null) {
            robMoney += internalRob(root.right.left, memo);
            robMoney += internalRob(root.right.right, memo);
        }
        int ans = Math.max(robMoney, internalRob(root.left, memo) + internalRob(root.right, memo));
        memo.put(root, ans);
        return ans;
    }

    public static void main(String[] args) {
        RobTreeNode robTreeNode = new RobTreeNode();
        TreeNode root = robTreeNode.new TreeNode(3);
        TreeNode node1 = robTreeNode.new TreeNode(2);
        TreeNode node2 = robTreeNode.new TreeNode(3);
        TreeNode node3 = robTreeNode.new TreeNode(3);
        TreeNode node4 = robTreeNode.new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node2.right = node4;
        System.out.println(robTreeNode.rob(root));
    }


}
