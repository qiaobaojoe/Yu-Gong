package org.example.yugong.leetCode.recursion;

import org.example.yugong.leetCode.ListNode;

import java.awt.*;

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

        if (left == 1) {
            return reverse(head, right);
        }

        head.next = reverseBetween(head.next, left - 1, right - 1);

        return head;
    }

//    1,2,3,4,5  2    3
    public ListNode reverse(ListNode node,  int right) {
        if (node.next == null) {
            return node;
        }
        if (right == 1) {
            return node;
        }
        ListNode cur = reverse(node.next, right - 1);
        ListNode tem = node.next.next;
        node.next.next = node;
        node.next = tem;
        return cur;
    }

}
