package leetcode.mals.tasks.binarysearch;

public class BinarySearch {

    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    public int binarySearch(int[] nums, int left, int right, int target) {

        if (left == right) {
            return nums[left] == target ? left : -1;
        }

        int i = left + (right - left) / 2;

        if (target <= nums[i]) {
            return binarySearch(nums, left, i , target);
        } else {
            return binarySearch(nums, i + 1, right, target);
        }
    }
}
