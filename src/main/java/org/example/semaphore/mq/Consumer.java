package org.example.semaphore.mq;

import lombok.SneakyThrows;

/**
 * @author qiaobao
 * @since 2025/6/22
 */
public class Consumer implements Runnable {
    private final SynchronizeBlockQueue topicQueue;

    private final String consumerGroupName;

    private final int gapTime;

    public Consumer(SynchronizeBlockQueue topicQueue, String consumerGroupName,int gapTIme) {
        this.topicQueue = topicQueue;
        this.consumerGroupName = consumerGroupName;
        this.gapTime = gapTIme;
    }

    @Override
    public void run() {
        for(;;){
//            6s消费一次
            try {
                String receive = topicQueue.receive();
                System.out.println(consumerGroupName + "消费了: " + receive);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(gapTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
