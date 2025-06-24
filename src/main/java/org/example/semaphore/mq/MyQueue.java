package org.example.semaphore.mq;


/**
 * @author qiaobao
 * @since 2025/6/23
 */
public interface MyQueue {

    void send(String message);

    String receive();

}
