package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Search In Rotated Sorted Array (see about page 98, already exist)
 * suppose an array sorted in ascending order is rotated at some pivot unknown to you.
 * [0, 1, 2, 4, 5, 6, 7] might become [4, 5, 6, 7, 0 ,1 ,2]
 *
 * @see LeetCode081
 */
public class LeetCode033 {

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            if (nums[start] < nums[mid]) { // mid is on the left side

                if (nums[start] <= target && nums[mid] >= target) end = mid;
                else start = mid;
            } else if (nums[mid] < nums[end]) { //mid is on the right side
                if (nums[end] >= target && nums[mid] <= target) start = mid;
                else end = mid;
            }
        }
        if (nums[end] == target) return end;
        if (nums[start] == target) return start;
        return -1;
    }

    @Test
    public void test1() {
        int ret = LeetCode033.search(new int[]{4,5,6,7,0,1,2}, 5);
        Assert.assertEquals(1, ret);
    }

}
