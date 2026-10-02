package leetcode.mals.tasks.trees;

import leetcode.mals.others.TreeNode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class KthSmallestElementInBST {

    public int kthSmallest(TreeNode root, int k) {

        Deque<TreeNode> queue = new ArrayDeque<>();
        PriorityQueue<TreeNode> heap = new PriorityQueue<>(Comparator.comparingInt((TreeNode i) -> i.val).reversed());
        int heapSize = 0;

        queue.offer(root);
        heap.offer(root);
        int levelSize = queue.size();

        while (!queue.isEmpty()) {
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {

                    queue.offer(node.left);

                    if (heap.size() < k) {
                        heap.offer(node.left);
                    } else if (node.left.val < heap.peek().val) {
                        heap.poll();
                        heap.offer(node.left);
                    }
                }

                if (node.right != null) {

                    queue.offer(node.right);

                    if (heap.size() < k) {
                        heap.offer(node.right);
                    } else if (node.right.val < heap.peek().val) {
                        heap.poll();
                        heap.offer(node.right);
                    }
                }
            }
        }

        return heap.peek().val;
    }
}
