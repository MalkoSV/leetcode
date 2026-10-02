package leetcode.mals.tasks.prefixtree;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarray {

    public int shortestSubarray(int[] nums, int k) {
        int result = nums.length + 1;
        int[] prefixSum = new int[nums.length + 1];

        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < prefixSum.length; i++) {

            while (queue.isEmpty() || (prefixSum[queue.peekLast()] - prefixSum[queue.peekFirst()] < k && i < prefixSum.length)) {

                while (!queue.isEmpty() && prefixSum[i] <= prefixSum[queue.peekLast()]) {
                    queue.pollLast();
                };

                queue.offer(i);
                i++;
            }

            if (prefixSum[queue.peekLast()] - prefixSum[queue.peekFirst()] >= k) {
                i--;
            }

            while (!queue.isEmpty() && prefixSum[queue.peekLast()] - prefixSum[queue.peekFirst()] >= k) {
                int subarrayLength = queue.peekLast() - queue.peekFirst();

                if (subarrayLength == 1 || prefixSum[queue.peekFirst() + 1] - prefixSum[queue.peekFirst()] >= k) {
                    return 1;
                }

                result = Math.min(subarrayLength, result);

                queue.poll();
            }

        }

        return result <= nums.length ? result : -1;
    }


    public int shortestSubarray2(int[] nums, int k) {
        int result = nums.length + 1;
        int[] prefixSum = new int[nums.length + 1];

        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < prefixSum.length; i++) {

            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.pollLast();
            };

            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {

                int length = i - deque.pollFirst();

                if (length == 1) {
                    return 1;
                }

                result = Math.min(result, length);
            }

            deque.offerLast(i);
        }

        return result <= nums.length ? result : -1;
    }

}
