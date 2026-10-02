package leetcode.mals.tasks.trees;

import leetcode.mals.others.TreeNode;

public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {

       return isValidWithBounds(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidWithBounds(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        return  (
                node.val > min
                        && node.val < max
                        && isValidWithBounds(node.left, min, node.val)
                        && isValidWithBounds(node.right, node.val, max)
        );
    }

}
