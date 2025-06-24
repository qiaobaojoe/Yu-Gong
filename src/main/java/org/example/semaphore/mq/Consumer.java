package org.example.semaphore.mq;

/**
 * @author qiaobao
 * @since 2025/6/22
 */
public class Consumer implements Runnable {
    private final MyQueue topicQueue;

    private final String consumerGroupName;

    private final int gapTime;

    public Consumer(MyQueue topicQueue, String consumerGroupName,int gapTime) {
        this.topicQueue = topicQueue;
        this.consumerGroupName = consumerGroupName;
        this.gapTime = gapTime;
    }

    @Override
    public void run() {
        for(;;){
//            6s消费一次
            String receive = topicQueue.receive();
            System.out.println(consumerGroupName + "消费了: " + receive);
            try {
                Thread.sleep(gapTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
