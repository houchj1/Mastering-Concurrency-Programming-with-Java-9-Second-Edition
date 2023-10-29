package leetcode2;

import com.sun.source.tree.Tree;
import leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Binary tree zigzag level order traversal
 */
public class LeetCode103 {

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;

        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean leftToRight = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            if (leftToRight) {
                for (int i = 0; i < size; i++) {
                    TreeNode curr = queue.remove(0);
                }
            }




        }
        return res;
    }

}
