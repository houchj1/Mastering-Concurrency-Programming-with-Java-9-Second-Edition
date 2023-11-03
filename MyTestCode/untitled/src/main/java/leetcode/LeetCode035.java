package leetcode;

/**
 * Search insert position.
 * binary search, be careful for some boundary condition 2, 3.
 * given a sorted array and a target, return the index if the target is found. If not, return the index where it were
 * inserted in the order. [1,3,5,6], 5 -> 2   [1,3,5,6], 2 -> 1
 */
public class LeetCode035 {

    static int searchInsert(int[] nums, int target) {
        if (nums ==null || nums.length == 0) return 0;
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) /2;
            if (nums[mid] < target) start = mid;
            else if (nums[mid] > target) end = mid;
            else return mid;
        }
        if (nums[end] < target) return end + 1;
        else if (nums[start] >= target) return start;
        else return end;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 5));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 0));

        System.out.println(searchInsert(new int[]{1,3,5,6}, 7));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 6));
    }
}
