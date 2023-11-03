package leetcode;

import leetcode.common.ListNode;

import java.util.Stack;

/**
 * reverse nodes in k groups.
 * similar as swap nodes in pairs, reverse the nodes of a linked list k at a time and return the modified list. k is a
 * positive number and is less than the length of the linked list. If the number of the nodes is not a multiple of k
 * then left-out nodes, in the end, should remain the same.
 */
public class LeetCode025 {

    static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null) return null;

        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode current = dummy;
        ListNode next = dummy.next;

        while (next != null) {
            for (int i = 0; i < k && next != null; i++) {
                stack.push(next);
                next = next.next;
            }
            if (stack.size() != k) return dummy.next;

            while (stack.size() > 0) {
                current.next = stack.pop();
                current = current.next;
            }
            current.next = next;

        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode res = reverseKGroup(ListNode.initFrom(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), 3);
        System.out.println(res.toNormalString());
    }

}
