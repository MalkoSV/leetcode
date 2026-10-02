package leetcode.mals.tasks.trees;

import leetcode.mals.others.TreeNode;

public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        Balance balance = new Balance();

        calculateHeight(root, balance);

        return balance.isValid;
    }


    private int calculateHeight(TreeNode node, Balance balance) {

        if (node == null || !balance.isValid) {
            return -1;
        }

        int leftDeep = 1 + calculateHeight(node.left, balance);
        int rightDeep = 1 + calculateHeight(node.right, balance);

        if (Math.abs(leftDeep - rightDeep) > 1) {
            balance.isValid = false;
        }

        return Math.max(leftDeep, rightDeep);
    }

    private class Balance {
        boolean isValid = true;
    }
}
