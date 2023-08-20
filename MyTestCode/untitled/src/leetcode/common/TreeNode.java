package leetcode.common;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode() {}

    public static TreeNode initFromArray(int[][] nums) {
        TreeNode root = new TreeNode(nums[0][0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < nums.length; i++) {
            int j = 0;
            Queue<TreeNode> newQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                node.left = new TreeNode(nums[i][j++]);
                node.right = new TreeNode(nums[i][j++]);
                newQueue.offer(node.left);
                newQueue.offer(node.right);
            }
            queue = newQueue;
        }
        return root;
    }
}
