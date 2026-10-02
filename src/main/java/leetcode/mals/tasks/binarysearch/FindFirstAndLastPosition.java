package leetcode.mals.tasks.binarysearch;

public class FindFirstAndLastPosition {

    public int[] searchRange(int[] nums, int target) {

        int maxIndex = nums.length - 1;

        if (nums.length == 0) {
            return new int[] {-1, -1};
        }

        int index = binarySearch(nums, 0, maxIndex, target);

        if (index == -1) {
            return new int[] {-1, -1};
        }

        int left = index;
        int right = index;
        boolean isFound = false;

        if (left > 0) {
            int newLeft;
            do {
                newLeft = binarySearch(nums, 0, left - 1, target);
                isFound = newLeft >= 0;

                if (isFound) {
                    left = newLeft;
                } else {
                    break;
                }

            } while (left > 0);
        }

        if (right < maxIndex) {
            int newRight;
            do {
                newRight = binarySearch(nums, right + 1, maxIndex, target);
                isFound = newRight >= 0;

                if (isFound) {
                    right = newRight;
                } else {
                    break;
                }

            } while (right < maxIndex);
        }

        return new int[] {left, right};
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
