package leetcode.mals.tasks.dynamicprogramming;

import java.util.Arrays;

public class CoinChangeDP {

    public int coinChange(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;

            for (int coin : coins) {

                int prev = i - coin;
                if (prev < 0) {
                    continue;
                }

                int value = dp[prev];

                if (value != -1) {
                    min = Math.min(value, min);
                }
            }

            if (min != Integer.MAX_VALUE) {
                dp[i] = min + 1;
            }

        }

        return dp[amount];
    }
}
