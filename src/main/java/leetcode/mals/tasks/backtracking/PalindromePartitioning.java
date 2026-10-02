package leetcode.mals.tasks.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<String> current = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();

        backtrack(nums, 0, current, result);

        return result;

    }

    private void backtrack(
            int[] nums,
            int idx,
            List<Integer> current,
            List<List<Integer>> result) {

        if (idx == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        Set<Integer> added = new HashSet<>(current);


        if (!added.contains(nums[idx])) {
            backtrack(nums, idx + 1, current, result);
        }

        current.add(nums[idx]);
        backtrack(nums, idx + 1, current, result);

        current.remove(current.size() - 1);
    }

}
