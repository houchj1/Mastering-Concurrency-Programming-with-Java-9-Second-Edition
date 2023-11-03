package leetcode;

import leetcode.common.ListNode;
import org.junit.Test;

/**
 * Partition List, separate the list by x,
 * but still keeps the order in original list
 *
 *
 */
public class LeetCode086 {

    public static ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode leftDummy = new ListNode(-1);
        ListNode leftCurr = leftDummy;
        ListNode rightDummy = new ListNode(-1);
        ListNode rightCurr = rightDummy;

        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                leftCurr.next = curr;
                leftCurr = curr;
            } else {
                rightCurr.next = curr;
                rightCurr = curr;
            }
            curr = curr.next;
        }
        if (leftDummy.next == null) {
            return rightDummy.next;
        } else if (rightDummy.next == null) {
            return leftDummy.next;
        } else {
            leftCurr.next = rightDummy.next;
            rightCurr.next = null;
            return  leftDummy.next;
        }
    }

    @Test
    public void test1() {
        ListNode res = LeetCode086.partition(ListNode.initFrom(new int[]{1,4,3,2,5,2}), 3);
        System.out.println(res.toNormalString());
    }
}
