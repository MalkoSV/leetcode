package leetcode.mals.tasks.binarysearch;

import java.util.function.Predicate;

public class FindFirstAndLastPosition2 {

    public int[] searchRange(int[] nums, int target) {

        if (nums.length == 0) {
            return new int[] {-1, -1};
        }

        int first = searchFirst(nums, 0, nums.length - 1, target);
        int last;

        if (first == -1) {
            return new int[] {-1, -1};
        } else {
            last = searchLast(nums, first, nums.length - 1, target);
        }

        return new int[] {first, last};
    }

    public int searchFirst(int[] nums, int start, int end, int target) {

        if (start == end) {
            return target == nums[start] ? start : -1;
        }

        int i = start + (end - start) / 2;

        if (nums[i] == target && (i == 0 || nums[i - 1] < nums[i])) {
            return i;
        }

        if (target <= nums[i]) {
            return i == 0 ? -1 : searchFirst(nums, start, i - 1, target);
        } else {
            return searchFirst(nums, i + 1, end, target);
        }
    }


    public int searchLast(int[] nums, int start, int end, int target) {

        if (start == end) {
            return target == nums[start] ? start : -1;
        }

        int i = start + (end - start) / 2;

        if (nums[i] == target && (i == nums.length - 1 || nums[i] < nums[i + 1])) {
            return i;
        }

        if (target < nums[i]) {
            return searchLast(nums, start, i - 1, target);
        } else {
            return searchLast(nums, i + 1, end, target);
        }
    }


}
