package leetcode;


import leetcode.common.ListNode;

/**
 * swap 2 nodes in pairs
 * Swap every two adjacent nodes and return its head. You many not modify the node values.
 */
public class LeetCode024 {

    static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        dummy.next = head;

        while (current.next != null && current.next.next != null) {
            swap2(current);
            current = current.next.next;
        }
        return dummy.next;
    }

    static void swap2(ListNode pre) {
        ListNode dummy = pre.next;
        pre.next = pre.next.next;
        dummy.next = dummy.next.next;
        pre.next.next = dummy;
    }

    public static void main(String[] args) {
        ListNode res = swapPairs(ListNode.initFrom(new int[] {1,2,3,4,5,6,7}));
        System.out.println(res.toNormalString());
    }
}
