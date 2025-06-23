package org.example.semaphore.mq;

import lombok.SneakyThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author qiaobao
 * @since 2025/6/22
 */
public class Producer implements Runnable {

    private final SynchronizeBlockQueue topicQueue;

    private final String producerGroupName;

    private final int gapTime;


    public Producer(SynchronizeBlockQueue topicQueue, String producerGroupName,int gapTime) {
        this.topicQueue = topicQueue;
        this.producerGroupName = producerGroupName;
        this.gapTime = gapTime;
    }

    @SneakyThrows
    @Override
    public void run() {
        for(;;){
            topicQueue.send(producerGroupName + "生产的消息：" + LocalDateTime.now());
            Thread.sleep(gapTime);
        }
    }
}
