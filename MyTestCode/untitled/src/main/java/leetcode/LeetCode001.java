package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode001 {

    public static int[] twoSum(int[] nums, int target) {
        int[] ret = new int[2];
        if (nums == null || nums.length == 0) return ret;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int val = target - nums[i];
            if (map.containsKey(val)) {
                ret[0] = i;
                ret[1] = map.get(val);
                return  ret;
            } else {
                map.put(nums[i], i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 5, 6, 7, 8, 9, 0};
        int[] ret = twoSum(nums, 11);
        Arrays.stream(ret).forEach(System.out::println);
    }
}
