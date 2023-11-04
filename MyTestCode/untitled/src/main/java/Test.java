import leetcode.common.ListNode;

import java.util.*;

public class Test {

    public static List<List<Integer>> combination(int[] nums, int k) {

        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0 || k <= 0) return res;

        helper(res, new ArrayList<>(), nums, 0, k);

        return res;
    }
    private static void helper(List<List<Integer>> res, List<Integer> curr, int[] nums,
                               int start_num, int k) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
        } else {

            for (int i = start_num; i < nums.length; i++) {
                curr.add(nums[i]);
                helper(res, curr, nums, i + 1, k);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(combination(new int[]{1,2,3}, 2));
    }

}