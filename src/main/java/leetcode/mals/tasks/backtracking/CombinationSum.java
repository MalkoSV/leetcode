package leetcode.mals.tasks.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> current = new ArrayList<>();

        backtrack(0, candidates, target, current, 0, set);

        return set.stream().toList();
    }

    private void backtrack(
            int idx,
            int[] candidates,
            int target,
            List<Integer> current,
            int sum,
            Set<List<Integer>> set) {

        if (sum == target) {
            set.add(new ArrayList<>(current));
            return;
        }

        if (sum > target || idx == candidates.length) {
            return;
        }

        backtrack(idx + 1, candidates, target, current, sum, set);

        current.add(candidates[idx]);
        sum += candidates[idx];
        backtrack(idx, candidates, target, current, sum, set);
        backtrack(idx + 1, candidates, target, current, sum, set);

        current.remove(current.size() - 1);
    }

}
