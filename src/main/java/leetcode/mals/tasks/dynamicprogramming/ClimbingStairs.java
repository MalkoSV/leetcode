package leetcode.mals.tasks.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {

    public int climbStairs(int n) {

        Map<Integer, Integer> map = new HashMap<>();

        return countWays(n, map);

    }

    private int countWays(int rest, Map<Integer, Integer> map) {
        if (rest == 1) {
            return 1;
        }

        if (rest == 2) {
            return 2;
        }

        if (!map.containsKey(rest - 1)) {
            map.put(rest - 1, countWays(rest - 1, map));
        }

        if (!map.containsKey(rest - 2)) {
            map.put(rest - 2, countWays(rest - 2, map));
        }
        return map.get(rest - 1) + map.get(rest - 2);
    }

}
