package leetcode.mals.tasks.binarysearch;

import java.util.function.Predicate;

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int maxIndex = nums.length - 1;
        boolean isRotated = nums[maxIndex] < nums[0];
        int index;

        if (!isRotated) {
            return nums[0];
        } else {
            return nums[searchFirst(nums, 0, maxIndex, i -> nums[i] < nums[0])];
        }
    }

    public int searchFirst(int[] nums, int start, int end, Predicate<Integer> predicate) {

        if (start == end) {
            return predicate.test(start)
                    ? start
                    : (start == nums.length - 1) ? -1 : start + 1;
        }

        int i = start + (end - start) / 2;

        if (predicate.test(i)) {
            return i == start
                    ? start
                    : searchFirst(nums, start, i - 1, predicate);
        } else {
            return searchFirst(nums, i + 1, end, predicate);
        }
    }
}
