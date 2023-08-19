package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Subset ||  (some duplicates in the nums) (*****)
 * TODO: understand it
 * @see
 */
public class LeetCode090 {

    public static List<List<Integer>> subsetWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        subsetHelper(nums, 0, true, res, new ArrayList<>());
        return res;
    }

    public static void subsetHelper(int[] nums, int currIdx, boolean taken,
                                    List<List<Integer>> res, List<Integer> curr) {

        if (currIdx == nums.length) {
            res.add(new ArrayList<>(curr));
        } else {
            subsetHelper(nums, currIdx + 1, false, res, curr);
            //use the taken to avoid duplicates
            if (taken || nums[currIdx - 1] != nums[currIdx]) {
                curr.add(nums[currIdx]);
                subsetHelper(nums, currIdx + 1, true, res, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> res = LeetCode090.subsetWithDup(new int[] {1,2,2});
        System.out.println(res);
    }
}
