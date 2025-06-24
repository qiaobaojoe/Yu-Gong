package org.example.semaphore.mq;

import lombok.SneakyThrows;
import java.time.LocalDateTime;

/**
 * @author qiaobao
 * @since 2025/6/22
 */
public class Producer implements Runnable {

    private final MyQueue topicQueue;

    private final String producerGroupName;

    private final int gapTime;


    public Producer(MyQueue topicQueue, String producerGroupName, int gapTime) {
        this.topicQueue = topicQueue;
        this.producerGroupName = producerGroupName;
        this.gapTime = gapTime;
    }

    @SneakyThrows
    @Override
    public void run() {
        int time = 1;
        for(;;){
            topicQueue.send(producerGroupName + "生产的消息：" + time);
            time++;
            Thread.sleep(gapTime);
        }
    }
}
