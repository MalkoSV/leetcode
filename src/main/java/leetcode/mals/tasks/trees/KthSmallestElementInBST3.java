package leetcode.mals.tasks.trees;

import leetcode.mals.others.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInBST3 {

    public int kthSmallest(TreeNode root, int k) {
        Counter counter  = new Counter();
        findBST(root, counter, k);

        return counter.result;
    }

    private void findBST(TreeNode node, Counter counter, int k) {

        if (node != null && counter.val < k) {
            findBST(node.left, counter, k);

            if (counter.val >= k) {
                return;
            }

            counter.val++;
            if (counter.val == k) {
                counter.result = node.val;
                return;
            }

            findBST(node.right, counter, k);
        }
    }

    private class Counter {
        public int val;
        public int result;
    }
}
