package leetcode.mals.tasks.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
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

        Set<Integer> thisLevelNums = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (added[i] || thisLevelNums.contains(nums[i])) {
                continue;
            }

            current.add(nums[i]);
            added[i] = true;
            thisLevelNums.add(nums[i]);

            backtrack(nums, current, added, result);

            current.remove(current.size() - 1);
            added[i] = false;
        }
    }
}
