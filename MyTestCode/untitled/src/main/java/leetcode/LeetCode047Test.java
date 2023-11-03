package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Permutations II
 * 	the nums contains duplicates. also not sorted.
 * 	The most important is to remove duplicate sets.
 *  duplicate (the numbers originate from nums are not call duplicated,
 *  the exactly same permutation are call duplicated)
 */
public class LeetCode047Test {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        Arrays.sort(nums);
        permuteHelper(nums, new boolean[nums.length], new ArrayList<>(), res);

        return res;
    }

    private static void permuteHelper(int[] nums, boolean[] used, List<Integer> curList,
                                      List<List<Integer>> res) {
        if (curList.size() == nums.length) {
            res.add(new ArrayList<>(curList));
        } else {
            int preNum = nums[0] - 1;
            for (int idx = 0; idx < nums.length; idx++) {
                if (used[idx] == false && nums[idx] != preNum) {
                    preNum = nums[idx];
                    curList.add(nums[idx]);
                    used[idx] = true;
                    permuteHelper(nums, used, curList, res);
                    used[idx] = false;
                    curList.remove(curList.size() - 1);
                }
            }
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> res = LeetCode047Test.permuteUnique(new int[] {1,1,2});
        System.out.println(res);

        res = LeetCode047Test.permuteUnique(new int[] {1,2});
        System.out.println(res);

        res = LeetCode047Test.permuteUnique(new int[] {1,1});
        System.out.println(res);
    }

}
