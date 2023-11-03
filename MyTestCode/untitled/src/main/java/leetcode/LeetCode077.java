package leetcode;

import leetcode.common.Interval;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Combinations:   *****
 * given 2 integers n and k, return all possible combinations of k numbers out of 1 … n.
 * a permutation when the order of selection is a factor, a combination when order is not a factor
 *
 * @see LeetCode046Test
 * @see LeetCode047Test
 * @see LeetCode031
 */
public class LeetCode077 {

    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0) return res;
        combinationHelper(n, k, 1, res, new ArrayList<>());
        return res;
    }

    public static void combinationHelper(int n, int k, int st_num, List<List<Integer>> res,
                                         List<Integer> currSeq) {
        if (k == 0) res.add(new ArrayList<>(currSeq));
        else {
            for (int i = st_num; i <= n; i++) {
                currSeq.add(i);
                combinationHelper(n, k - 1, i+1, res, currSeq);
                currSeq.remove(currSeq.size() - 1);
            }
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> res = LeetCode077.combinations(3, 2);
        System.out.println(res);

        res = LeetCode077.combinations(4, 2);
        System.out.println(res);
    }
}
