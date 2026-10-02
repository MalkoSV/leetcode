package leetcode.mals.tasks.trees;

import leetcode.mals.others.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LowestCommonAncestorOfBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Counter counter = new Counter();
        buildNodeChain(root, p, q, counter);

        if (!counter.isFoundP || !counter.isFoundQ) {
            return null;
        }

        return findLowestAncestor(counter.stackP, counter.stackQ);
        }

    private void buildNodeChain(TreeNode node, TreeNode p, TreeNode q, Counter counter) {
        if (node == null) {
            return;
        }

        if (!counter.isFoundP) {
            counter.stackP.push(node);

            if (node == p) {
                counter.isFoundP = true;
            }
        }

        if (!counter.isFoundQ) {
            counter.stackQ.push(node);

            if (node == q) {
                counter.isFoundQ = true;
            }
        }

        if (!counter.isFoundP || !counter.isFoundQ) {
            buildNodeChain(node.left, p, q, counter);
        }
// *********************************************************************************

        if (!counter.isFoundP || !counter.isFoundQ) {
            if (node.left != null) {

                if (!counter.isFoundP) {
                    counter.stackP.poll();
                }

                if (!counter.isFoundQ) {
                    counter.stackQ.poll();
                }
            }
            buildNodeChain(node.right, p, q, counter);

            if (node.right != null) {

                if (!counter.isFoundP) {
                    counter.stackP.poll();
                }

                if (!counter.isFoundQ) {
                    counter.stackQ.poll();
                }
            }
        }
    }

    private TreeNode findLowestAncestor(Deque<TreeNode> stackP, Deque<TreeNode> stackQ) {
        int delta = stackP.size() - stackQ.size();

        if (delta > 0) {
            for (int i = 0; i < delta; i++) {
                stackP.pop();
            }
        }

        if (delta < 0) {
            for (int i = 0; i < -delta; i++) {
                stackQ.pop();
            }
        }

        while (!stackP.isEmpty()) {
            TreeNode first = stackP.pop();
            TreeNode second = stackQ.pop();

            if (first == second) {
                return first;
            }
        }

        return null;
    }

    private class Counter {
        boolean isFoundP;
        boolean isFoundQ;
        Deque<TreeNode> stackP = new ArrayDeque<>();
        Deque<TreeNode> stackQ = new ArrayDeque<>();
    }

}
