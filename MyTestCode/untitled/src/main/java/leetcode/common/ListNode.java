package leetcode.common;

import java.util.Arrays;
import java.util.List;

public class ListNode {

    public int val;

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode() {
        this.val = 0;
    }


    public static ListNode initFrom(int[] nums) {
        ListNode header = new ListNode();
        ListNode prev = header;
        for (int num : nums) {
            ListNode it = new ListNode(num);
            prev.setNext(it);
            prev = it;
        }
        return header.next;
    }

    /**
     * a int string is in a reverse order, and take it whole as int, first node is the lowest bit of int
     * @return
     */
    public String toIntString() {
        String s = "]";
        ListNode list = this;
        while (list != null) {
            s = (list.val + ", ") + s;
            list = list.next;
        }
        s = ("[") + s;
        return s;
    }

    @Override
    public String toString() {
        return this.toNormalString();
    }

    /**
     * the normal order first node is on the left
     * @return
     */
    public String toNormalString() {
        StringBuilder sb = new StringBuilder("[");
        ListNode list = this;
        while (list != null) {
            sb.append(list.val + ", ");
            list = list.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
