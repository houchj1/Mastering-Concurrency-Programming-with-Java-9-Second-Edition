package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Permutations.
 * the numbers are ordered and unique,
 * given a collection of distinct numbers, return all possible permutations
 *
 */
public class LeetCode046Test {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper(nums, res, new ArrayList<>());

        return res;
    }

    private static void helper(int[] nums, List<List<Integer>> res, List<Integer> curr) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!curr.contains(nums[i])) { // exclude the added one to avoid duplicates
                    curr.add(nums[i]);
                    helper(nums, res, curr);  // every time recurse back to index 0
                    curr.remove(curr.size() - 1);  // remove current one and recurse
                }
            }
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> res = LeetCode046Test.permute(new int[]{1,2,3});
        System.out.println(res);
    }

}
