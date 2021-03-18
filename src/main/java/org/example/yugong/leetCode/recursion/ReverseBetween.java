package org.example.yugong.leetCode.recursion;

import org.example.yugong.leetCode.textExample.ListNode;


/**
 * @author qiaobao
 * @since 2021-03-16
 */
public class ReverseBetween {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }

        if (left == right) {
            return head;
        }

        int count = 0;
        ListNode cur = head;
        ListNode pre = null;
        ListNode first = null;
        ListNode reverseStart = null;
        while (count < right) {
            count++;
            if (count < left) {
                cur = cur.next;
                continue;
            }
            if (count == left) {
                first = pre;
                pre = null;
                reverseStart = cur;
            }
            if (count == right) {
                break;
            }
            cur.next = pre;
            pre = cur;
            cur = cur.next;

        }
        ListNode next = cur.next;
        cur.next = pre;
        reverseStart.next = next;

        if (first != null) {
            first.next = cur;
        } else {
            return cur;
        }

        return head;
    }


}
