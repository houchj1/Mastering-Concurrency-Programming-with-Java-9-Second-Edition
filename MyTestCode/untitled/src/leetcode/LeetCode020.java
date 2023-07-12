package leetcode;

import java.util.Stack;

/**
 * valid parentheses
 */
public class LeetCode020 {

    static boolean isValid(String s) {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char curr = stack.pop();

                if (curr == '(' && c != ')') return false;
                if (curr == '[' && c != ']') return false;
                if (curr == '{' && c != '}') return false;

            }
        }
        if (stack.isEmpty()) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isValid("(("));

        System.out.println(isValid("(())"));

        System.out.println(isValid("((]]))"));

    }

}
