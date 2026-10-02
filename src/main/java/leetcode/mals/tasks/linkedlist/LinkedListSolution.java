package leetcode.mals.tasks.linkedlist;

import leetcode.mals.others.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LinkedListSolution {

    public static ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {

            if (l1.val <= l2.val) {
                tail.next = l1;
                tail = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = l2;
                l2 = l2.next;
            }
        }

        tail.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public boolean hasCycle(ListNode head) {
        Set<ListNode> listNodes = new HashSet<>();

        while (head != null) {
            if (!listNodes.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public boolean hasCycleFloyd(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode current = head;
        Map<Integer, ListNode> nodes = new HashMap<>();
        int i = 0;

        while (current != null) {
            nodes.put(i, current);
            current = current.next;
            i++;
        }

        int size = nodes.size();

        if (n == size) {
            return head.next;
        }

        ListNode deleted = nodes.get(size - n);
        ListNode beforeDeleted = nodes.get(size - n - 1);
        beforeDeleted.next = deleted.next;

        return head;
    }

    public ListNode removeNthFromEndByFloyd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> values = new ArrayList<>();

        while (head != null) {
            values.add(head.val);
            head = head.next;
        }

        for (int left = 0, right = values.size() - 1; left < right; left++, right--) {
            if (!values.get(left).equals(values.get(right))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindromeByFastSlow(ListNode head) {

        // Dummy simplifies middle detection:
        // after the loop, slow.next is always the start of the second half.
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = dummy;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode current = slow.next;

        ListNode prev = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        ListNode reversedHead = prev;

        while (reversedHead != null) {
            if (head.val != reversedHead.val) {
                return false;
            }
            head = head.next;
            reversedHead = reversedHead.next;
        }

        return true;
    }

    public ListNode swapPairs(ListNode head) {

        if (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            head = second;

            while (true) {
                first.next = second.next;
                second.next = first;
                ListNode nextPairFirst = first.next;

                if (nextPairFirst == null || nextPairFirst.next == null) {
                    break;
                }

                first.next = nextPairFirst.next;

                first = nextPairFirst;
                second = first.next;
           }
        }
        return head;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        for (int i = 1; i < left ; i++) {
            prev = prev.next;
        }

        ListNode before = prev;
        ListNode first = before.next;

        int nodesToReverse = right - left + 1;
        ListNode reversedHead = before;
        ListNode current = before.next;

        for (int i = 1; i <= nodesToReverse ; i++) {
            ListNode next = current.next;
            current.next = reversedHead;
            reversedHead = current;
            current = next;
        }

        before.next = reversedHead;
        first.next = current;

        return dummy.next;
    }

    public ListNode sortList(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        int count = 0;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            if (first.val > second.val) {
                first.next = second.next;
                second.next = first;
                prev.next = second;
            }

            prev = prev.next;
            count++;
        }

        for (int i = 1; i < count; i++) {
            prev = dummy;

            for (int j = 1; j <= count - i; j++) {
                ListNode first = prev.next;
                ListNode second = first.next;

                if (first.val > second.val) {
                    first.next = second.next;
                    second.next = first;
                    prev.next = second;
                }

                prev = prev.next;
            }
        }
        return dummy.next;
    }
}
