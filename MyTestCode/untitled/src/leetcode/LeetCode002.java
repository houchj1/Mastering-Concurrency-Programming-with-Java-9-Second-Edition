package leetcode;

import leetcode.common.ListNode;

public class LeetCode002 {

    public static ListNode add2Numbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {

            ListNode curr = new ListNode();
            int sum = (l1 == null ? 0: l1.val) + (l2 == null ? 0: l2.val) + carry;
            curr.val = sum % 10;
            carry = sum / 10;
            prev.next = curr;
            prev = curr;

            l1 = (l1 ==null? null : l1.next);
            l2 = (l2 ==null? null : l2.next);
        }

        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = ListNode.initFrom(new int[] {1,2,3});
        System.out.println(l1.toIntString());

        ListNode l2 = ListNode.initFrom(new int[]{6, 7, 9});
        System.out.println(l2.toIntString());

        ListNode ret = add2Numbers(l1, l2);

        System.out.println(ret.toIntString());
    }

}
