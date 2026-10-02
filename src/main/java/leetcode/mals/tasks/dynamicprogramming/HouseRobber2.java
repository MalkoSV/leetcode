package leetcode.mals.tasks.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber2 {

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();

        return countMoney(0, nums, map);
    }

    private int countMoney(int index, int[] nums,  Map<Integer, Integer> map) {
        if (nums.length - index == 1) {
            return nums[index];
        }

        if (nums.length - index == 2) {
            return Math.max(nums[index], nums[index + 1]);
        }

        if (nums.length - index == 3) {
            return Math.max(nums[index] + nums[index + 2], nums[index + 1]);
        }

        if (!map.containsKey(index + 2)) {
            map.put(index + 2, countMoney(index + 2, nums, map));
        }

        if (!map.containsKey(index + 3)) {
            map.put(index + 3, countMoney(index + 3, nums, map));
        }

        return Math.max(
                nums[index] + map.get(index + 2),
                nums[index + 1] + map.get(index + 3)
        );
    }
}
