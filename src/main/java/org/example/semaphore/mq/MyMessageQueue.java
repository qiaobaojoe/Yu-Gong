package org.example.semaphore.mq;


import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author qiaobao
 * @since 2025/6/22
 */
public class MyMessageQueue {
    //    消费队列的两个作用：削峰填谷  解耦 今天在这里实现阻塞队列，及时理解Java的同步机制，也是为整体的理解rocketMq的预热
    private static SynchronizeBlockQueue topic = new SynchronizeBlockQueue();

    public static void main(String[] args) {
        Producer producer = new Producer(topic, "生产者1号",5000);
        Producer producer2 = new Producer(topic, "生产者2号",3000);
        Consumer consumer1 = new Consumer(topic, "消费者1号",2000);
        Consumer consumer2 = new Consumer(topic, "消费者2号",1000);
        ExecutorService executorService = newFixedThreadPool(4);
        executorService.submit(producer);
        executorService.submit(producer2);
        executorService.submit(consumer1);
        executorService.submit(consumer2);


    }


}
