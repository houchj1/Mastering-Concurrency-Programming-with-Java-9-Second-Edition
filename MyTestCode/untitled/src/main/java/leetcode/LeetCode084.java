package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Largest Rectangle in Histogram
 */
public class LeetCode084 {

    /**
     * O(n^2) cannot pass leetcode
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int max = 0;
        for (int curr = 0; curr < heights.length; curr++) {
            // only start to calculate when there is next height is smaller
            // as if next height is larger, we can still get larger when move next
            if (curr == heights.length - 1 || heights[curr] > heights[curr + 1]) {
                int minHeight = heights[curr];
                for (int idx = curr; idx >= 0; idx--) {
                    minHeight = Math.min(minHeight, heights[idx]);
                    max = Math.max(max, minHeight * (curr - idx + 1));
                }
            }
        }
        return max;
    }

    @Test
    public void test1() {
        int area = LeetCode084.largestRectangleArea(new int[]{2, 4, 6, 5, 3});
        Assert.assertEquals(12, area);
    }
}
