package org.example.yugong.thread;

import org.example.yugong.exception.CustomException;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiaobao
 * @since 2020-01-12
 */
public class ExecutorExceptionDemo {

    public static ThreadPoolExecutor poolExecutor = new CustomExecutor(
            5,
            10,
            0,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(400),
            new DemoThreadFactory());

    public static AtomicInteger slat = new AtomicInteger();


    public static void action() throws InterruptedException {
        System.out.println("start");
        for (int i = 0; i < 10; i++) {
             poolExecutor.execute(() -> {
                int andIncrement = slat.getAndIncrement();
                System.out.println(Thread.currentThread().getName() + ">>>>>" + slat);
                if (andIncrement % 2 == 1) {
                    throw new CustomException("test" + slat);
                }
            });


        }

        System.out.println("end");

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        action();
    }


}
