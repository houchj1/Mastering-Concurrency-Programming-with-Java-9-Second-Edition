package leetcode2;

import leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * binary tree level order traverse
 */
public class LeetCode102 {

    public static List<List<Integer>> leveOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeNode.initFromArray(new int[][] {
                {1},
                {20,37},
                {4,5,6,7}
        });
        System.out.println(leveOrder(tree));

    }

}
