package leetcode.mals.tasks.dynamicprogramming;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;

        for (int i = 1; i < nums.length; i++) {
            int max = 0;

            for (int j = i - 1; j >= 0 ; j--) {

                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }

            dp[i] = max + 1;
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}
