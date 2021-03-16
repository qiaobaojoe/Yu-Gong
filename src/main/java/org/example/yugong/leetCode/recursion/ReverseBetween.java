package org.example.yugong.leetCode.recursion;

import org.example.yugong.leetCode.ListNode;

/**
 * @author qiaobao
 * @since 2021-03-16
 */
public class ReverseBetween {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head==null){
            return head;
        }

        if(left==right){
            return head;
        }

        int count =0;
        ListNode tem = head;
        while(tem.next!=null){
            count++;
            if(count<left-1){
                tem = tem.next;
                continue;
            }

            if(left==1){
                ListNode cur= reverse(tem,0,right);
                return cur;
            }

            ListNode cur= reverse(tem.next,count,right);

            tem.next = cur;
            break;
        }

        return head;
    }

    public ListNode reverse(ListNode node,int count,int right){
        count++;
        if(node.next==null){
            return node;
        }
        if(count == right){
            return node;
        }

        ListNode cur = reverse(node.next,count,right);
        ListNode tem = node.next.next;
        node.next.next = node;
        node.next = tem;
        return cur;
    }

}
