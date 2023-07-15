package leetcode;


import leetcode.common.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * merge K sorted list
 */
public class LeetCode023 {


    static class NodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    static ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode();
        if (lists == null || lists.length ==0) return dummy.next;
        int size = lists.length;

        ListNode curr = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue(new NodeComparator());
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }

        while(pq.size() >0) {
            ListNode node = pq.poll();
            curr.next = node;
            curr = node;

            if (node.next != null) pq.add(node.next);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[]{
                ListNode.initFrom(new int[] {1,5, 7, 9}),
                ListNode.initFrom(new int[] {2, 4, 7, 8, 10})
        };

        ListNode res = mergeKLists(lists);

        System.out.printf(res.toNormalString());
    }
}
