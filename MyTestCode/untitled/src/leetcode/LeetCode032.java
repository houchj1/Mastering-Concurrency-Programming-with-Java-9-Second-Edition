package leetcode;

import java.util.Stack;

/**
 * Longest valid parentheses
 * contains only parentheses, but in different orders
 */
public class LeetCode032 {

    static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        int max = 0;
        int leftmost = -1;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    leftmost = i;
                } else {
                    int j = stack.pop();
                    if (stack.isEmpty()) max = Math.max(max, i - leftmost);
                    else max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("()"));
        System.out.println(longestValidParentheses("()()"));
        System.out.println(longestValidParentheses("(())"));
        System.out.println(longestValidParentheses("((()"));
        System.out.println(longestValidParentheses("))"));
        System.out.println(longestValidParentheses("("));
        System.out.println(longestValidParentheses("))((()))"));
    }
}
