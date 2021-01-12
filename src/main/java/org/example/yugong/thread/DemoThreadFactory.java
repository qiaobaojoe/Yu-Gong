package org.example.yugong.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiaobao
 * @since 2021-01-12
 */
public class DemoThreadFactory implements ThreadFactory {

    private AtomicLong sum = new AtomicLong();


    @Override
    public Thread newThread(Runnable r) {
        long andIncrement = sum.getAndIncrement();
        Thread thread = new Thread(r);
        thread.setName("demo-thread" + andIncrement);
        return thread;
    }

}
