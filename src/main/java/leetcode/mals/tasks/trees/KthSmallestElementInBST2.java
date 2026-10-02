package leetcode.mals.tasks.trees;

import leetcode.mals.others.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

public class KthSmallestElementInBST2 {

    public int kthSmallest(TreeNode root, int k) {
        Counter count  = new Counter();
        List<Integer> nodeValues = findBST(root, count, k);

        return nodeValues.get(k - 1);
    }

    private List<Integer> findBST(TreeNode node, Counter count, int k) {
        if (node == null) {
            return List.of();
        }

        if (count.val >= k) {
            return List.of();
        }

        List<Integer> result = new ArrayList<>(findBST(node.left, count, k));
        result.add(node.val);
        count.val++;
        result.addAll(findBST(node.right, count, k));

        return result;
    }

    private class Counter {
        public int val;
    }
}
