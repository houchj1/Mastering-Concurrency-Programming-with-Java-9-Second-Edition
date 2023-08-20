package leetcode;

import leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Same Tree (*)
 */
public class LeetCode100 {

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;
        else {
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    @Test
    public void test1() {
        TreeNode p = TreeNode.initFromArray(new int[][]{{1}, {2,3}, {4,5,6,7}});
        TreeNode q = TreeNode.initFromArray(new int[][]{{1}, {2,3}, {4,5,6,7}});
        boolean ret = LeetCode100.isSameTree(p, q);
        Assert.assertEquals(true, ret);

        q.right.left = null;
        ret = LeetCode100.isSameTree(p, q);
        Assert.assertEquals(false, ret);
    }
}
