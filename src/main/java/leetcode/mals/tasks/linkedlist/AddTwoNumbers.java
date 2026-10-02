package leetcode.mals.tasks.linkedlist;

import leetcode.mals.others.ListNode;

public class AddTwoNumbers {
    static ListNode l13 = new ListNode(3);
    static ListNode l12 = new ListNode(4, l13);
    static ListNode l11 = new ListNode(2, l12);
    static ListNode l23 = new ListNode(4);
    static ListNode l22 = new ListNode(6, l23);
    static ListNode l21 = new ListNode(5, l22);
    static ListNode r13 = new ListNode(8);
    static ListNode r12 = new ListNode(0, r13);
    static ListNode r11 = new ListNode(7, r12);

    static ListNode l31 = new ListNode(0);
    static ListNode l41 = new ListNode(0);
    static ListNode r31 = new ListNode(0);

    static ListNode l57 = new ListNode(9);
    static ListNode l56 = new ListNode(9, l57);
    static ListNode l55 = new ListNode(9, l56);
    static ListNode l54 = new ListNode(9, l55);
    static ListNode l53 = new ListNode(9, l54);
    static ListNode l52 = new ListNode(9, l53);
    static ListNode l51 = new ListNode(9, l52);
    static ListNode l64 = new ListNode(9);
    static ListNode l63 = new ListNode(9, l64);
    static ListNode l62 = new ListNode(9, l63);
    static ListNode l61 = new ListNode(9, l62);
    static ListNode r58 = new ListNode(1);
    static ListNode r57 = new ListNode(0, r58);
    static ListNode r56 = new ListNode(0, r57);
    static ListNode r55 = new ListNode(0, r56);
    static ListNode r54 = new ListNode(9, r55);
    static ListNode r53 = new ListNode(9, r54);
    static ListNode r52 = new ListNode(9, r53);
    static ListNode r51 = new ListNode(8, r52);


    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;

            current.next = new ListNode(sum % 10);
            current = current.next;

        }

        return dummy.next;
    }

    public static void decideTask() {
        ListNode l1 = addTwoNumbers(l11, l21);
        ListNode l2 = addTwoNumbers(l31, l41);
        ListNode l3 = addTwoNumbers(l51, l61);

        while (l1 != null) {
            System.out.print(l1.val + ", ");
            l1 = l1.next;
        }
        System.out.println();


        while (l2 != null) {
            System.out.print(l2.val + ", ");
            l2 = l2.next;
        }
        System.out.println();


        while (l3 != null) {
            System.out.print(l3.val + ", ");
            l3 = l3.next;
        }
        System.out.println();

    }
}
