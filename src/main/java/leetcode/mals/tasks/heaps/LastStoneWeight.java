package leetcode.mals.tasks.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int st : stones) {
            heap.offer(st);
        }

        while (heap.size() >= 2) {
            int highest = heap.poll();
            int delta = highest - heap.poll();

            if (delta > 0) {
                heap.offer(delta);
            }
        }
         return !heap.isEmpty() ? heap.peek() : 0;
    }

}
