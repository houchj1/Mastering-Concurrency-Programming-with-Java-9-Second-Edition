package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Combination sum 2.
 * difference is that it has duplicate elements,
 * we need to remove the duplicate result set.  Time Complexity is O(2^n)
 *
 */
public class LeetCode040 {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;

        List<Integer> curList = new ArrayList<>();
        Arrays.sort(candidates);

        combinationSum2Helper(candidates, target, 0, curList, res);
        return res;
    }

    public static void combinationSum2Helper(int[] candidates, int target, int index,
                                             List<Integer> curList, List<List<Integer>> res)
    {
        if (target == 0) {
            res.add(new ArrayList<>(curList));
        } else if (target > 0) {
            for (int i = index; i < candidates.length; i++) {
                if (i != index && candidates[i] == candidates[i-1]) continue;
                curList.add(candidates[i]);
                combinationSum2Helper(candidates, target - candidates[i], i + 1, curList, res);
                curList.remove(curList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{3,2,1}, 3));
        System.out.println(combinationSum2(new int[]{3,2,2,1}, 3));
        System.out.println(combinationSum2(new int[]{3,2,2,2,1,1}, 3));
    }

}
