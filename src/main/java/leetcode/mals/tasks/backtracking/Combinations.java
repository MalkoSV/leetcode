package leetcode.mals.tasks.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        backtrack(1, n, k, current, result);

        return result;
    }

    private void backtrack(
            int idx,
            int n,
            int k,
            List<Integer> current,
            List<List<Integer>> result) {

        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        int need = k - current.size();
        int rested = n - idx + 1;


        if (rested < need) {
            return;
        }

        backtrack(idx + 1, n, k, current, result);

        current.add(idx);
        backtrack(idx + 1, n, k, current, result);

        current.remove(current.size() - 1);
    }

}
