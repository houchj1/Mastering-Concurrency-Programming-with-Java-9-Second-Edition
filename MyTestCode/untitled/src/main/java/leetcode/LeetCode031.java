package leetcode;

import java.util.Arrays;

/**
 * Next permutation.
 * rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (sorted in ascending order).
 * The replacement must be in-place. [1,2,3] ->[1, 3, 2]  [3, 2, 1] ->[1,2,3]  [1,1,5] -> [1,5,1]
 */

public class LeetCode031 {

    static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int replace = nums.length - 2;
        while (replace >= 0) {
            if (nums[replace] < nums[replace + 1]) break;
            replace--;
        }
        if (replace < 0) {
            Arrays.sort(nums);
            return;
        }
        int lgrIdx = replace + 1;
        while (lgrIdx < nums.length && nums[lgrIdx] > nums[replace]) {
            lgrIdx++;
        }
        int tmp = nums[replace];
        nums[replace] = nums[lgrIdx - 1];
        nums[lgrIdx - 1] = tmp;
        Arrays.sort(nums, replace + 1, nums.length);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,6,4,2};
        nextPermutation(nums);
        Arrays.stream(nums).forEach(System.out::print);

        System.out.println();
        nums = new int[]{5,4,3,2,1};
        nextPermutation(nums);
        Arrays.stream(nums).forEach(System.out::print);

        System.out.println();
        nums = new int[]{1,2,3,4,5};
        nextPermutation(nums);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
