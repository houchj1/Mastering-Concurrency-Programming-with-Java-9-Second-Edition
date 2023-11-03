package leetcode;

import java.util.Arrays;

/**
 * find first and last position of elements in sorted array
 * there are duplicated elements, search the index range of the target. use
 * binary search. O(log(n))
 */
public class LeetCode034 {

    static int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return res;
        int st_point = -1, end_point = -1;
        int start = 0, end = nums.length - 1;

        //binary search to find the start point first
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) end = mid;
            else start = mid;
        }

        if (nums[start] == target) st_point = start;
        else if (nums[end] == target) st_point = end;
        if (st_point == -1) return res;

        //use binary search to find the end point again
        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) end = mid;
            else start = mid;
        }
        if (nums[end] == target) end_point = end;
        else if (nums[start] == target) end_point = start;
        res[0] = st_point;
        res[1] = end_point;

        return res;
    }

    public static void main(String[] args) {
        Arrays.stream(searchRange(new int[]{1,2,2,2,3,4}, 2)).forEach(System.out::print);
        System.out.println();

        Arrays.stream(searchRange(new int[]{1,2,2,2,3,4}, 1)).forEach(System.out::print);
        System.out.println();

        Arrays.stream(searchRange(new int[]{1,2,2,2,3,4}, 4)).forEach(System.out::print);
        System.out.println();

        Arrays.stream(searchRange(new int[]{1,2,2,2,3,4}, 5)).forEach(System.out::print);
    }

}
