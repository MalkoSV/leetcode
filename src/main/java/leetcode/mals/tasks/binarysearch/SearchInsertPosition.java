package leetcode.mals.tasks.binarysearch;

public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);

    }

    public int binarySearch(int[] nums, int left, int right, int target) {

        if (left == right) {
            if (left == nums.length - 1 && target > nums[left]) {
                return nums.length;
            }

            return left;
        }

        int i = left + (right - left) / 2;

        if (target <= nums[i]) {
            return binarySearch(nums, left, i , target);
        } else {
            return binarySearch(nums, i + 1, right, target);
        }
    }

}
