package leetcode;

import java.util.Arrays;

/**
 * Remove Element
 * <p>
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The relative order of the elements may be changed.
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 */
public class LeetCode027 {

    static int removeElement(int[] nums, int val) {
        if (nums == null) return 0;
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[slow++] = nums[i];
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,8,3,2,5};
        System.out.println(removeElement(nums, 2));
        Arrays.stream(nums).forEach(System.out::println);

    }


}
