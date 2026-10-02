package leetcode.mals.tasks.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {

        int[] left = new int[heights.length];
        int[] right = new int[heights.length];

        Deque<Integer> rightStack = new ArrayDeque<>();
        Deque<Integer> leftStack = new ArrayDeque<>();

        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {

            while (!rightStack.isEmpty() && heights[i] <= heights[rightStack.peek()]) {
                right[rightStack.pop()] = i;
            }
            rightStack.push(i);

            while (!leftStack.isEmpty() && heights[i] <= heights[leftStack.peek()]) {
                leftStack.pop();
            }

            if (leftStack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = leftStack.peek();
            }

            leftStack.push(i);
        }

        while (!rightStack.isEmpty()) {
            right[rightStack.pop()] = heights.length;
        }

        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max((right[i] - left[i] - 1) * heights[i], maxArea);
        }

        return maxArea;
    }

    public int largestRectangleArea2(int[] heights) {

        Deque<Integer> stack = new ArrayDeque<>();

        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {

            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int right = i;
                int left = !stack.isEmpty() ? stack.peek() : -1;
                maxArea = Math.max((right - left - 1) * height, maxArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int right = heights.length;
            int left = !stack.isEmpty() ? stack.peek() : -1;
            maxArea = Math.max((right - left - 1) * height, maxArea);
        }

        return maxArea;
    }

}
