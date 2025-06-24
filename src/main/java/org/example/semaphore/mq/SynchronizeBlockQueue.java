package org.example.semaphore.mq;

import lombok.SneakyThrows;

import java.util.ArrayDeque;

/**
 * @author qiaobao
 * @since 2025/6/22
 */
public class SynchronizeBlockQueue implements MyQueue {

    private ArrayDeque<String> messageList = new ArrayDeque<>();

    private final Object semaphore = new Object();


    @Override
    public void send(String message) {
        synchronized (semaphore) {
            boolean needNotify = messageList.isEmpty();
            messageList.add(message);
            if (needNotify) {
                semaphore.notifyAll();
            }
        }
    }

    @SneakyThrows
    @Override
    public String receive() {
        synchronized (semaphore) {
            while (messageList.isEmpty()) {
                semaphore.wait();
            }
            return messageList.poll();
        }

    }
}
