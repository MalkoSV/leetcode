package leetcode.mals.tasks.binarysearch;

import java.util.function.Predicate;

public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {

        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int maxIndex = nums.length - 1;
        boolean isRotated = nums[maxIndex] < nums[0];
        int index;

        if (!isRotated) {
            index = searchFirst(nums, 0, maxIndex, i -> nums[i] >= target);
        } else {
            int start2 = searchFirst(nums, 0, maxIndex, i -> nums[i] < nums[0]);

            if (target >= nums[0]) {
                index = searchFirst(nums, 0, start2 - 1, i -> nums[i] >= target);
            } else {
                index = searchFirst(nums, start2, maxIndex, i -> nums[i] >= target);
            }
        }
        if (index == -1 || nums[index] != target) {
            return -1;
        }
        return index;
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
