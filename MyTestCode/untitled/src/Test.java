import leetcode.common.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {

    public static ListNode mergeKSortedList(ListNode[] lists) {

        if (lists == null || lists.length == 0) return null;

        class ListComparator implements Comparator<ListNode> {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new ListComparator());
        for (int i = 0; i < lists.length; i++) {
            pq.offer(lists[i]);
        }
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (pq.size() > 0) {
            curr.next = pq.poll();
            curr = curr.next;
            if (curr.next != null)
                pq.offer(curr.next);
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[]{
                ListNode.initFrom(new int[]{1,3,5}),
                ListNode.initFrom(new int[]{2,4,6}),
                ListNode.initFrom(new int[]{3,5,7})
        };
        System.out.println(mergeKSortedList(lists));
    }
}