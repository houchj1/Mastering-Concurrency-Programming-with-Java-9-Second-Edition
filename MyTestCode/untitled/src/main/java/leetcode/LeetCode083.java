package leetcode;

import leetcode.common.ListNode;
import org.junit.Test;

/**
 * Remove duplicates from sorted list
 * 	given 1-1-2 return 1-2, given 1-1-2-3-3 return 1-2-3
 */
public class LeetCode083 {

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode iter = head;
        while (iter != null && iter.next != null) {
            if (iter.val == iter.next.val) {
                iter.next = iter.next.next;
            } else {
                iter = iter.next;
            }
        }
        return head;
    }

    @Test
    public void test1() {
        ListNode res = LeetCode083.deleteDuplicates(ListNode.initFrom(new int[]{1,1,2}));
        System.out.println(res.toNormalString());

        res = LeetCode083.deleteDuplicates(ListNode.initFrom(new int[]{1,1,2,3,3}));
        System.out.println(res.toNormalString());
    }

}
