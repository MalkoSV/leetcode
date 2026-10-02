package leetcode.mals.tasks.binarysearch;

public class SearchInRotatedSortedArray2 {

    public int search(int[] nums, int target) {

        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int maxIndex = nums.length - 1;

        if (target > nums[maxIndex] && target < nums[0]) {
            return -1;
        }

        return searchTarget(nums, 0, maxIndex, target);
    }

    public int searchTarget(int[] nums, int start, int end, int target) {

        if (end - start == 1) {
            if (nums[start] == target) {
                return start;
            }

            if (nums[end] == target) {
                return end;
            } else {
                return -1;
            }
        }

        int i = start + (end - start) / 2;

        if (nums[start] <= nums[i]) {
            if (nums[start] <= target && target <= nums[i]){
                end = i;
            } else {
                start = i;
            }
        } else {
            if (nums[i] <= target && target <= nums[end]){
                start = i;
            } else {
                end = i;
            }
        }

        return searchTarget(nums, start, end, target);
    }
}
