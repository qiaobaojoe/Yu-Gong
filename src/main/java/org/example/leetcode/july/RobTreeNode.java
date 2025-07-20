package org.example.leetcode.july;

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
        // 普通打家劫舍的求当前状态和核心就是
        // dp[i][1] 今天偷到  = dp[0][i-2] + nums[i]
        // dp[i][0] = dp[i-1][1]
        // ans = max(dp[i][1],dp[i][1])
        // 这里有两个问题需要解决
        // 动态方程的大小提起不能确定，因为二叉树的层级和节点都不能提前确定
        // 遍历的路上不是线性迭代的，当前节点状态，可以会影响关联两个子节点的状态
        // 很巧妙的递归，要理解这个并不容易
        if (root == null) {
            return 0;
        }
        int robCur = root.val;
        if (root.left != null) {
            robCur += rob(root.left.left);
            robCur += rob(root.left.right);
        }
        if (root.right != null) {
            robCur += rob(root.right.left);
            robCur += rob(root.right.right);
        }

        return Math.max(robCur, rob(root.left) + rob(root.right));
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
