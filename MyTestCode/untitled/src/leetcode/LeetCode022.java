package leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, generate all combinations of well-formed parentheses.
 */
public class LeetCode022 {

    static List<String> generateParentheses(int n) {
        List<String> res = new ArrayList<>();

        helper("", res, n, 0, 0);
        return res;
    }

    /**
     *
     * @param curr
     * @param res
     * @param n
     * @param left how many left parentheses is used
     * @param right how many right parentheses is used, if all right side is used then complete one combination.
     */
    static void helper(String curr, List<String> res, int n, int left, int right) {
        if (right == n) {
            res.add(curr);
            return;
        }
        if (left < n) {
            helper(curr+"(", res, n, left + 1, right);
        }
        if (right < left) {
            helper(curr + ")", res, n, left, right + 1);
        }
    }

    public static void main(String[] args) {
        List<String> res = generateParentheses(3);
        res.stream().forEach(System.out::println);
    }
}
