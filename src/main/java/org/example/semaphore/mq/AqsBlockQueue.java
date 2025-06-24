package org.example.semaphore.mq;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qiaobao
 * @since 2025/6/22
 * 最强版本的生产者消费者模型的有界队列
 * 通过两个信号量控制
 */
public class AqsBlockQueue implements MyQueue {

    private final Semaphore empty = new Semaphore(0);

    private final Semaphore full;

    /**
     * 生产者插入数据的下标
     */
    private Integer in = 0;

    /**
     * 消费者取出数据的下标
     */
    private Integer out = 0;

    private final String[] buffer;
    private final Integer maxSize;

    private ReentrantLock mutex = new ReentrantLock();

    public AqsBlockQueue(int maxSize) {
        this.maxSize = maxSize;
        this.buffer = new String[maxSize];
        this.full = new Semaphore(maxSize);
    }

    @SneakyThrows
    @Override
    public void send(String message) {
//        对full进行p操作，也就是 -1
        full.acquire();
        mutex.lock();
        try {
            buffer[in] = message;
//        这里必须要取模才可以循环起来
            in = (in + 1) % maxSize;
        } finally {
            mutex.unlock();
        }
//        对empty进行v操作，也就 +1
        empty.release();
    }

    @SneakyThrows
    @Override
    public String receive() {
        empty.acquire();
        String message;
        mutex.lock();
        try {
            message = buffer[out];
            out = (out + 1) % maxSize;
        } finally {
            mutex.unlock();
        }
        full.release();
        return message;
    }
}
