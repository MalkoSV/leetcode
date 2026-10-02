package leetcode.mals.tasks.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> right = new PriorityQueue<>();
    double median = 0.0;

    public MedianFinder() {
    }

    public void addNum(int num) {
        if (num <= median) {
            left.offer(num);
        } else {
            right.offer(num);
        }

        median = calculateMedian();
    }

    public double findMedian() {
        return median;
    }

    private double calculateMedian() {
        switch (left.size() - right.size()) {
            case 2 -> {
                right.offer(left.poll());
                return (left.peek() + right.peek()) / 2.0;
            }
            case 1 -> {
                return left.peek();
            }
            case 0 -> {
                return (left.peek() + right.peek()) / 2.0;
            }
            case -1 -> {
                return right.peek();
            }
            case -2 -> {
                left.offer(right.poll());
                return (left.peek() + right.peek()) / 2.0;
            }
        }
        return 0.0;
    }
}
