package leetcode.mals.tasks.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargest2 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.size() == k ? heap.peek() : 0;
    }

}
