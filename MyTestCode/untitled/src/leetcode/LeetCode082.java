package leetcode;

import leetcode.common.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove duplicates from sorted list II (the duplicated are totally removed)
 * 2 implementations
 *
 * @see LeetCode083
 */
public class LeetCode082 {

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);

        ListNode preNode = dummy;
        ListNode curNode = head;
        ListNode realNode = dummy;  // the node we needed
        while (curNode != null) {
            // curNode do not equal to pre and next, so curr node can be real
            if ((preNode == dummy || preNode.val != curNode.val)
                && (curNode.next == null || curNode.val != curNode.next.val)) {

                realNode.next = curNode;
                realNode = curNode;
            }
            preNode = curNode;
            curNode = curNode.next;
            preNode.next = null;
        }
        return dummy.next;
    }

    /**
     * another implement, easier to understand
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        List<Integer> duplicates = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            if (curr.next != null && curr.val == curr.next.val) duplicates.add(curr.val);
            curr = curr.next;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        curr = head;
        while (curr != null) {
            if (!duplicates.contains(curr.val)) {
                pre.next = curr;
                pre = curr;
            }
            curr = curr.next;
        }
        pre.next = null;
        return dummy.next;
    }

    @Test
    public void test1() {
        ListNode res = LeetCode082.deleteDuplicates(ListNode.initFrom(new int[]{1,2,3,3,4,4,5}));
        System.out.println(res.toNormalString());

        res = LeetCode082.deleteDuplicates2(ListNode.initFrom(new int[]{1,2,3,3,4,4,5}));
        System.out.println(res.toNormalString());
    }
}
