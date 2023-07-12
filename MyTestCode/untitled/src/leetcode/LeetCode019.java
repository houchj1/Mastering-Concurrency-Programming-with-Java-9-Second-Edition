package leetcode;

import leetcode.common.ListNode;

/**
 * remove Nth node from the end of the list
 */
public class LeetCode019 {

    static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.initFrom(new int[]{1,2,3,4,5,6});
        ListNode res = removeNthFromEnd(list, 2);

        System.out.println(res.toNormalString());
    }


}
