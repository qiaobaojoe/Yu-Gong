package org.example.semaphore.mq;

import java.util.ArrayDeque;

/**
 * @author qiaobao
 * @since 2025/6/22
 */
public class SynchronizeBlockQueue {

    private ArrayDeque<String> messageList = new ArrayDeque<>();

    private final Object semaphore = new Object();


    public void send(String message) {
        synchronized (semaphore) {
            boolean needNotify = messageList.isEmpty();
            messageList.add(message);
            if (needNotify) {
                semaphore.notifyAll();
            }
        }
    }

    public String receive() throws InterruptedException {
        synchronized (semaphore){
            while (messageList.isEmpty()) {
                semaphore.wait();
            }
            return messageList.poll();
        }

    }
}
