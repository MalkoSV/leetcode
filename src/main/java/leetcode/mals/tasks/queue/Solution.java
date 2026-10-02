package leetcode.mals.tasks.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] max = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < k - 1; i++) {
            while (!queue.isEmpty() && nums[i] > nums[queue.getLast()]) {
                queue.pollLast();
            }

            queue.offer(i);
        }

        for (int i = k - 1; i < nums.length; i++) {

            if (!queue.isEmpty() && queue.peek() <= i - k) {
                queue.poll();
            }

            while (!queue.isEmpty() && nums[i] > nums[queue.getLast()]) {
                queue.pollLast();
            }

            queue.offer(i);

            max[i - (k - 1)] = nums[queue.peek()];
        }

        return max;
    }

}
