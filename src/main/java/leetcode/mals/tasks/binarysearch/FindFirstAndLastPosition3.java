package leetcode.mals.tasks.binarysearch;

import java.util.function.Predicate;

public class FindFirstAndLastPosition3 {

    public int[] searchRange(int[] nums, int target) {

        if (nums.length == 0) {
            return new int[] {-1, -1};
        }

        Predicate<Integer> isFirst = i -> nums[i] >= target;
        Predicate<Integer> isLast = i -> nums[i] > target;

        int first = searchFirst(nums, 0, nums.length - 1, isFirst);

        if (first == -1) {
            return new int[] {-1, -1};
        }

        int last = searchFirst(nums, first, nums.length - 1, isLast);

        if (first == last) {
            return new int[] {-1, -1};
        }

        if (last == -1) {
            last = nums.length - 1;
        } else {
            last--;
        }

        return new int[] {first, last};
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
