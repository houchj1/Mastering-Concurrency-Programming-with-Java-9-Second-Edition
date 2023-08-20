package leetcode2;

import leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Minimum Depth Of Binary Tree
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 *
 */
public class LeetCode111 {
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        else if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.initFromArray(new int[][]{{1}, {2, 3}});
        int ret = minDepth(root);
        Assert.assertEquals(2, ret);
    }
}
