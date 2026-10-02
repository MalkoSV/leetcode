package leetcode.mals.tasks.heaps;

import java.util.PriorityQueue;

public class KthLargest {

    int k;
    PriorityQueue<Integer> queue;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.queue = new PriorityQueue<>();

        for (int num : nums) {

            queue.offer(num);

            if (queue.size() > k) {
                queue.poll();
            }
        }
    }

    public int add(int val) {

        queue.offer(val);

        if (queue.size() > k) {
            queue.poll();
        }

        return queue.size() == k ? queue.peek() : 0;
    }

}
