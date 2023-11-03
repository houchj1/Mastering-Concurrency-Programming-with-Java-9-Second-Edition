package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Simplify path
 */
public class LeetCode071 {

    public static String simplifyPath(String path) {
        if (path.length() <= 1) return path;
        String[] each = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String curr : each) {
            if (curr.equals("..")) {
                if (!stack.empty()) stack.pop();
            } else if (!curr.equals(".") && !curr.isEmpty()) {
                stack.push(curr);
            }
        }
        String ret = "";
        if (stack.empty()) return "/";
        while (!stack.empty()) {
            String newPop = stack.pop();
            ret = "/" + newPop + ret;
        }
        return ret;
    }

    @Test
    public void test1() {
        String ret = LeetCode071.simplifyPath("/a/b/../c");
        Assert.assertEquals("/a/c", ret);

        ret = LeetCode071.simplifyPath("/a/b/../../c");
        Assert.assertEquals("/c", ret);

        ret = LeetCode071.simplifyPath("/c/");
        Assert.assertEquals("/c", ret);
    }

}
