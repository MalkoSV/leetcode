package leetcode.mals.tasks.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        boolean[] added = new boolean[nums.length];

        backtrack(nums, current, added, result);

        return result;
    }

    private void backtrack(
            int[] nums,
            List<Integer> current,
            boolean[] added,
            List<List<Integer>> result
    ) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (added[i]) {
                continue;
            }

            current.add(nums[i]);
            added[i] = true;
            backtrack(nums, current, added, result);

            added[i] = false;
            current.remove(current.size() - 1);
        }
    }
}
