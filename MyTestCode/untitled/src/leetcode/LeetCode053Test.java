package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Max Subarray  (**********Codility has)
 * 	init value of maxToCurr and max should be first element, shouldn’t be 0
 * 	Find the contiguous subarray within an array (containing at least one number)
 * 	which has the largest sum.
 * 	[-2, 1, -3, 4, -1, 2, 1, -5, 4] the contiguous
 * 	subarray [4, -1, 2, 1] has the largest sum = 6
 */
public class LeetCode053Test {

    public static int maxSubArray(int[] nums) {
        int maxToCurr = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // could nums[i] is the largest, or nums[i] add up to previous largest till curr
            maxToCurr = Math.max(maxToCurr+nums[i], nums[i]);

            // the global max sum
            sum = Math.max(sum, maxToCurr);
        }
        return sum;
    }

    @Test
    public void test1() {
        int sum = LeetCode053Test.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        Assert.assertEquals(6, sum);


    }

}
