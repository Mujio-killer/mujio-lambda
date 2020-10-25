/*
package com.mujio.leetcode;

class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode res = new ListNode(0);
        addThreeNumbers(l1, l2, res);
        return res;
    }

    public void addThreeNumbers(ListNode l1, ListNode l2, ListNode res) {
        if (l1 == null && l2 == null) {
        }
        int temp = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
        res.val = temp % 10;
        if (l1.next != null || l2.next != null) {
            res.next = new ListNode(temp / 10);
            addThreeNumbers(l1.next, l2.next, res.next);
        }
        res.next = temp / 10 == 0 ? null : new ListNode(temp / 10);
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
*/
