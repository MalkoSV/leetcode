package leetcode.mals.tasks.dynamicprogramming;

import java.util.Arrays;

public class HouseRobber {

    public int rob(int[] nums) {
        int[] arr = new int[nums.length + 2];
        Arrays.fill(arr, -1);

        return countMoney(0, nums, arr);
    }

    private int countMoney(int index, int[] nums, int[] arr) {

        if (index >= nums.length) {
            return 0;
        }

        if (arr[index] != -1) {
            return arr[index];
        }

        if (arr[index + 1] == -1) {
            arr[index + 1] = countMoney(index + 1, nums, arr);
        }

        if (arr[index + 2] == -1) {
            arr[index + 2] = countMoney(index + 2, nums, arr);
        }

        int result = Math.max(
                nums[index] + arr[index + 2],
                arr[index + 1]
        );

        arr[index] = result;

        return result;
    }
}
