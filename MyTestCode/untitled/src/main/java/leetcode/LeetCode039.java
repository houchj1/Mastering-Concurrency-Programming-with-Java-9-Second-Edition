package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Combination sum (******)
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers
 * sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers
 * is different. (seems like new added constraints, no such in old version)
 *
 */
public class LeetCode039 {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target <= 0) return res;

        Arrays.sort(candidates);

        combinationSumHelper(candidates, target, 0, res, new ArrayList<Integer>());
        return res;
    }

    public static void combinationSumHelper(int[] candidates, int target, int index, List<List<Integer>> res,
                                            List<Integer> curSeq) {
        if (target == 0) {
            res.add(new ArrayList<>(curSeq));
        } else if (target > 0) {
            for (int i = index; i < candidates.length; i++) {
                if (candidates[i] > target) break;
                curSeq.add(candidates[i]);

                combinationSumHelper(candidates, target - candidates[i],
                        i, res, curSeq); // recurse still from i as the same item may appear different times
                curSeq.remove(curSeq.size() - 1);  // remove current and move to next main start point
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{4,2,3,1}, 3));
    }
}
