package leetcode.mals.tasks.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubsetsIIMap {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> added = new HashMap<>();

        backtrack(nums, 0, current, added, result);

        return result;
    }

    private void backtrack(
            int[] nums,
            int idx,
            List<Integer> current,
            Map<Integer, Integer> added,
            List<List<Integer>> result) {

        if (idx == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (!added.containsKey(nums[idx])) {
            backtrack(nums, idx + 1, current, added, result);
        }

        current.add(nums[idx]);
        added.merge(nums[idx], 1, Integer::sum );
        backtrack(nums, idx + 1, current, added, result);

        current.remove(current.size() - 1);
        added.computeIfPresent(nums[idx], (key, value) -> value > 1 ? value - 1 : null);
    }
}
