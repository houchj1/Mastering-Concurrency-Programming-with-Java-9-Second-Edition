package leetcode;

import leetcode.common.ListNode;
import org.junit.Test;

/**
 * Rotate list
 */
public class LeetCode061Test {

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        ListNode fast = head;
        ListNode slow = head;

        int len = 0;
        while (fast != null) {
            len++;
            fast = fast.next;
        }
        fast = head;
        for (int i = 0; i < k % len; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }

    @Test
    public void test1() {
        ListNode head = ListNode.initFrom(new int[]{1, 2, 3, 4, 5});
        ListNode res = LeetCode061Test.rotateRight(head, 2);
        System.out.println(res.toIntString());
    }
}
