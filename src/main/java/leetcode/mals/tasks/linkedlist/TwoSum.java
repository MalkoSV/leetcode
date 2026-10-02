package leetcode.mals.tasks.linkedlist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    static int[][] nums = {
            {2, 7, 11, 15},
            {3, 2, 4},
            {3, 3},
            {3, 13, -25, 8, 0, 224, -43}
    };

    static int[] target = {
            9,
            6,
            6,
            -25
    };

    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> collection = new HashMap<>();

        collection.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            int targetDelta = target - nums[i];

            if (collection.containsKey(targetDelta)) {
                return new int[]{collection.get(targetDelta), i};
            }

            collection.put(nums[i], i);
        }
        throw new IllegalArgumentException("The input arguments does not meet the requirements");
    }

    static int[] twoSumSimple(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            int targetDelta = target - nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == targetDelta) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("The input arguments does not meet the requirements");
    }

    public static void decideTask() {
        System.out.println("Variant using HashMap.");
        for (int i = 0; i < nums.length; i++) {
            System.out.println(Arrays.toString(twoSum(nums[i], target[i])));
        }
    }

    public static void decideTaskSimple() {
        System.out.println("Simple Variant.");
        for (int i = 0; i < nums.length; i++) {
            System.out.println(Arrays.toString(twoSumSimple(nums[i], target[i])));
        }
    }

}
