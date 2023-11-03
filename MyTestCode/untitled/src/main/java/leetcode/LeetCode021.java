package leetcode;

import leetcode.common.ListNode;

/**
 * merge 2 sorted list, the result should also be sorted
 */
public class LeetCode021 {

    static ListNode merge2List(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {

            boolean l1Smaller = l1.val < l2.val;
            curr.next = l1Smaller ? l1 : l2;
            if (l1Smaller) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 == null) curr.next = l2;
        else if (l2 ==null) curr.next = l1;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode res = merge2List(ListNode.initFrom(new int[]{1, 3, 5}), ListNode.initFrom(new int[]{2, 4, 6}));
        System.out.println(res.toNormalString());
    }

}
