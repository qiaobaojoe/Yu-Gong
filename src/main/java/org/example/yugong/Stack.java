package org.example.yugong;

/**
 * @author qiaobao
 * @since 2021-03-01
 */

import java.util.LinkedList;

public class Stack {
    LinkedList list = new LinkedList();

    public synchronized void push(Object x) {
        synchronized (list) {
            list.addLast(x);
            notify();
        }
    }

    public synchronized Object pop() throws Exception {
        synchronized (list) {
            if (list.size() <= 0) {
                wait();
            }
            return list.removeLast();
        }
    }
}
