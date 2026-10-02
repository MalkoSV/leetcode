package leetcode.mals.tasks.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class RecentCounter {

    Deque<Integer> queue = new ArrayDeque<>();

    public RecentCounter() {
    }

    public int ping(int t) {
        queue.offer(t);

        int timePoint = t - 3000;

        while (queue.peek() < timePoint) {
            queue.poll();
        }

        return queue.size();
    }
}
