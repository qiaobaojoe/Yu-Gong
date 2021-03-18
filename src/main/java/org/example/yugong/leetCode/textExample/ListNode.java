package org.example.yugong.leetCode.textExample;

/**
 * @author qiaobao
 * @since 2021-03-16
 */
public class ListNode {

    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode getExample() {
        ListNode cur = new ListNode(1);
        cur.next = new ListNode(2);
        cur.next.next = new ListNode(3);
        cur.next.next.next = new ListNode(4);
        cur.next.next.next.next = new ListNode(5);
        return cur;
    }
}
