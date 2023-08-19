package leetcode;

import leetcode.common.MyUtility;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Subsets I (no duplicates)  (*****)
 * given a 	set of distinct integers, return all possible subsets.
 * Must not contain duplicate subset.
 * Example given: [1, 2, 3],
 * a solution is: [[3], [1], [2], [1,2,3],[1,3],[2,3],[1,2],[]]
 */
public class LeetCode078 {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curSeq = new ArrayList<>();
        res.add(curSeq);

        if (nums == null || nums.length == 0) return res;
        for (int i = 1; i <= nums.length; i++) {
            helper(res, curSeq, nums, 0, i);
        }
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> curr, int[] nums,
                              int idx, int count) {
        if (curr.size() == count) {
            res.add(new ArrayList<>(curr));
        } else {
            for (int i = idx; i < nums.length; i++) {
                curr.add(nums[i]);
                helper(res, curr, nums, i + 1, count);
                curr.remove(curr.size() - 1);
            }
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> res = LeetCode078.subsets(new int[]{1, 2, 3});
        System.out.println(res);
    }
}
