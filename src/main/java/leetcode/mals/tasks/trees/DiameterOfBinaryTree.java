package leetcode.mals.tasks.trees;

import leetcode.mals.others.TreeNode;

public class DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        Diameter diameter = new Diameter();

        calculateDeep(root, diameter);

        return diameter.value;
    }

    private int calculateDeep(TreeNode node, Diameter diameter) {

        if (node == null) {
            return -1;
        }

        int leftDeep = 1 + calculateDeep(node.left, diameter);
        int rightDeep = 1 + calculateDeep(node.right, diameter);

        diameter.value = Math.max(leftDeep + rightDeep, diameter.value);

        return Math.max(leftDeep, rightDeep);
    }

    private class Diameter {
        int value;
    }
}
