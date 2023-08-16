package leetcode;


/**
 * first missing positive
 * use cardinal order
 */
public class LeetCode041 {

    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = Integer.MAX_VALUE;  // ignore minus and zero value
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num <= nums.length) {
                nums[num -1] = -Math.abs(nums[num - 1]); // convert to minus to preserve the value
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1; // the first positive is corresponding to i+1 missing positive
            }
        }
        return nums.length + 1; // all are in the range and sequential
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{1,2}));
        System.out.println(firstMissingPositive(new int[]{1,3}));
    }
}
