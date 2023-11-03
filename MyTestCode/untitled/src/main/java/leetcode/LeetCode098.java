package leetcode;


import leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * •	The left subtree of a node contains only nodes with keys less than the node's key.
 * •	The right subtree of a node contains only nodes with keys greater than the node's key.
 * •	Both the left and right subtrees must also be binary search trees.
 */
public class LeetCode098 {

    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return helper(root, null, null);
    }

    /**
     * min, max的意思是当前知道的min,max，如果不知道就是null
     * @param root
     * @param max
     * @param min
     * @return
     */
    private static boolean helper(TreeNode root, Integer max, Integer min) {
        if (root == null) return true;
        if (max != null && root.val >= max) return false;
        if (min != null && root.val <=min) return false;
        boolean left = helper(root.left, root.val, min);
        boolean right = helper(root.right, max, root.val);
        return left && right;
    }

    @Test
    public void test1() {

        boolean ret = LeetCode098.isValidBST(TreeNode.initFromArray(new int[][]{
                {3},
                {1, 5},
                {0,2,4,6}
        }));
        Assert.assertEquals(true, ret);

        ret = LeetCode098.isValidBST(TreeNode.initFromArray(new int[][]{
                {3},
                {1, 5},
                {1,2,4,6}
        }));
        Assert.assertEquals(false, ret);

    }

}
