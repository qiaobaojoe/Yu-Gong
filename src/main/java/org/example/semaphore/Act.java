package org.example.semaphore;

import org.example.semaphore.MyRunnable;

/**
 * @author qiaobao
 * @since 2025/6/24
 */
public class Act {


    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread thread1 = new Thread(myRunnable,"thread1");
        Thread thread2 = new Thread(myRunnable, "thread2");

        thread1.start();
        thread2.start();

    }
}
