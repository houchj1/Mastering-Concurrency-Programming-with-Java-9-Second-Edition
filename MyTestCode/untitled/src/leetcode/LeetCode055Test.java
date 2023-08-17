package leetcode;


import org.junit.Assert;
import org.junit.Test;

/**
 * Jump Game, @see LeetCode045
 * given an array of non-negative integers,
 * you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at the position.
 * Determine if you are able to reach the last index.
 */
public class LeetCode055Test {

    public static boolean canJump(int[] nums) {
        if (nums.length <2) return true;
        int reach = 0;
        int i = 0;
        for (i = 0; i < nums.length && i <= reach; i++) {
            reach = Math.max(nums[i] + i, reach);
            if (reach >= nums.length - 1) return true;
        }
        return false;
    }

    @Test
    public void test1() {
        boolean ret = LeetCode055Test.canJump(new int[] {3,2,1,0,4});
        Assert.assertEquals(false, ret);

        ret = LeetCode055Test.canJump(new int[] {3,2,1,1,4});
        Assert.assertEquals(true, ret);
    }
}
