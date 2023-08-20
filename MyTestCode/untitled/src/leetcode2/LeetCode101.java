package leetcode2;

import leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Symmetric Tree  (***** easy)
 */
public class LeetCode101 {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetricHelper(root.left, root.right);
    }
    private static boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return (left.val == right.val) && isSymmetricHelper(left.left, right.right)
                && isSymmetricHelper(left.right, right.left);
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.initFromArray(new int[][]{{1}, {3,3}, {4,5,5,4}});
        boolean ret = isSymmetric(root);
        Assert.assertEquals(true, ret);

        root = TreeNode.initFromArray(new int[][]{{1}, {3,3}, {4,5,6,4}});
        ret = isSymmetric(root);
        Assert.assertEquals(false, ret);
    }

}
