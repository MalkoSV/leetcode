package leetcode.mals.tasks.heaps;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.compute(n,  (key, value) -> value == null ? 1 : value + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue));

        heap.addAll(map.entrySet());

        while (heap.size() > k) {
            heap.poll();
        }

        int[] result = new int[heap.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = heap.poll().getKey();
        }

        return result;
    }

}
