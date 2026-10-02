package leetcode.mals.tasks.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        result.add(current);

        recursive(nums, 0, current, result);

        return result;
    }

    private void recursive(
            int[] nums,
            int idx,
            List<Integer> current,
            List<List<Integer>> subsets
    ) {
        if (idx == nums.length) {
            return;
        }

        List<Integer> newValue = new ArrayList<>(current);
        newValue.add(nums[idx]);
        subsets.add(newValue);

        recursive(nums,idx + 1, newValue, subsets);
        recursive(nums,idx + 1, current, subsets);
    }

    public List<List<Integer>> subsetsBt(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

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

        backtrack(nums, idx + 1, current, result);

        current.add(nums[idx]);
        backtrack(nums, idx + 1, current, result);

        current.remove(current.size() - 1);
    }

}
