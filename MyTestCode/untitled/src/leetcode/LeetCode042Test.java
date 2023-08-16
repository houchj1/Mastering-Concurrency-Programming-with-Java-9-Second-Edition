package leetcode;

import org.junit.Test;

/**
 * trapping rain water.
 * Very similar with LeetCode11, LeetCode11 is vertical line that the line itself do contain water.
 * LeetCode42, the bar height itself pushes water away.
 *
 */
public class LeetCode042Test {

    public static int trap(int[] height) {
        if (height == null || height.length == 0 || height.length < 3) return 0;
        int n = height.length;

        int[] leftmost = new int[n];
        int[] rightmost = new int[n];
        int premost = 0;

        for (int i = 0; i < n; i++) {
            leftmost[i] = premost;
            if (premost <= height[i]) {
                premost = height[i];
            }
        }
        premost = 0;
        for (int i = n -1; i >=0; i--) {
            rightmost[i] = premost;
            if (premost <= height[i]) {
                premost = height[i];
            }
        }

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int min = Math.min(leftmost[i], rightmost[i]);
            int water = min - height[i];
            if (water > 0)
                sum += water;
        }
        return sum;
    }

    @Test
    public void test1() {
        int ret = LeetCode042Test.trap(new int[]{1, 0, 2, 1, 3, 1, 4, 3, 1, 3});
        assert ret == 6;

        ret = LeetCode042Test.trap(new int[]{1, 0, 2});
        assert ret == 1;

        ret = LeetCode042Test.trap(new int[]{1, 1, 2});
        assert ret == 0;
    }
}
