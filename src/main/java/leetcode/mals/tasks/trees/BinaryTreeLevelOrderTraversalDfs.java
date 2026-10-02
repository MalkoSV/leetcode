package leetcode.mals.tasks.trees;

import leetcode.mals.others.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversalDfs {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root != null) {
            result.add(new ArrayList<>(List.of(root.val)));

            result.addAll(
                    merge(
                            levelOrder(root.left),
                            levelOrder(root.right)
                    )
            );

        } else {
            return new ArrayList<>(List.of());
        }

        return result;
    }

    private List<List<Integer>> merge(List<List<Integer>> left, List<List<Integer>> right) {
        List<List<Integer>> mergedList = new ArrayList<>();

        int size = Math.max(left.size(), right.size());

        if (size > 0) {
            for (int i = 0; i <= size - 1; i++) {
                List<Integer> l = i < left.size() ? left.get(i) : List.of();
                List<Integer> r = i < right.size() ? right.get(i) : List.of();

                List<Integer> sum = new ArrayList<>(l);
                sum.addAll(r);
                mergedList.add(sum);
            }
        }
        return mergedList;
    }

}
