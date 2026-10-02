package leetcode.mals.tasks.dynamicprogramming;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class CoinChangeBFS {

    public int coinChange(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }

        return bfs(coins, amount);

    }

    private int bfs(int[] coins, int amount) {
        int result = 1;
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();

        for (int coin : coins) {
            if (coin == amount) {
                return 1;
            }

            if (coin < amount) {
                queue.offer(coin);
                set.add(coin);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            result++;

            for (int i = 0; i < size; i++) {
                int sum = queue.poll();
                int rest = amount - sum;

                for (int c : coins) {

                    if (c == rest) {
                        return result;
                    }

                    int newSum = sum  + c;
                    if (c < rest && set.add(newSum)) {
                        queue.offer(newSum);
                    }
                }
            }

        }
        return -1;
    }
}
