package leetcode;


import org.junit.Assert;
import org.junit.Test;

/**
 * Remove Duplicates from Sorted Array ||
 * a number allows only once duplicate(2 times).
 * Given an integer array nums sorted in non-decreasing order,
 * remove some duplicates in-place such that each unique element appears at most twice.
 * The relative order of the elements should be kept the same.
 */
public class LeetCode080 {

    public static int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <= 2) return nums.length;

        // anyway the first 2 are accepted
        int slow = 2;
        for (int fast = 2; fast < nums.length; fast++) {
            // current one not equal to the previous 2 items of slow index
            if (!(nums[fast] == nums[slow - 2] && nums[fast] == nums[slow - 1])) {
                // put it at slow index and increase it
                nums[slow] = nums[fast];
                slow++;
            } //otherwise ignore fast index
        }
        return slow;
    }

    @Test
    public void test1() {
        int ret = LeetCode080.removeDuplicates(new int[]{1, 1, 1, 2, 2});
        Assert.assertEquals(4, ret);
    }

}
