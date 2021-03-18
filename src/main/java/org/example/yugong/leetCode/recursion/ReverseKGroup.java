package org.example.yugong.leetCode.recursion;

import org.example.yugong.leetCode.textExample.ListNode;

/**
 * @author qiaobao
 * @since 2021-03-17
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        if (head == null) {
            return null;
        }

        int index = k;
        ListNode cur = head;
        while (index != 0) {
            index--;

            if (cur == null) {
                return head;
            }
            cur = cur.next;
        }

        ListNode reverseNode = reverseKGroup(cur, k);
        ListNode reversek = reverseK(head, k);
        head.next = reverseNode;
        return reversek;

    }

    public ListNode reverseK(ListNode head, int k) {

        if (k == 1) {
            return head;
        }
        k = k - 1;
        ListNode cur = reverseK(head.next, k);
        head.next.next = head;
        head.next = null;
        return cur;

    }


    public static void main(String[] args) {
        ReverseKGroup reverseKGroup = new ReverseKGroup();
        ListNode listNode = reverseKGroup.reverseKGroup(ListNode.getExample(), 2);
        System.out.println(listNode);

    }
}
