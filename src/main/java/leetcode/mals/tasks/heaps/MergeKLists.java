package leetcode.mals.tasks.heaps;

import leetcode.mals.others.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparing(listNode -> listNode.val));

        for (ListNode node : lists) {

            if (node != null) {
                 heap.offer(node);
            }
        }

        ListNode head = heap.peek();

        while (!heap.isEmpty()) {
            ListNode current = heap.poll();

            if(current.next != null) {
                heap.offer(current.next);
            }

            current.next = heap.peek();
        }

        return  head;
    }

}
