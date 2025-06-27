package org.example.semaphore;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qiaobao
 * @since 2025/6/25
 */
public class DeadLockMock {

    private static ReentrantLock printer = new ReentrantLock();

    private static ReentrantLock scaner = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    printer.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println("尝试获取扫描仪");
                        scaner.lock();
                        try {
                            System.out.println("把文件扫描处理");
                        } finally {
                            scaner.unlock();
                        }
                    }finally {
                        printer.unlock();
                    }

                }
            });

        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                scaner.lock();
                try {
                    Thread.sleep(1000);
                    System.out.println("尝试获取打印机");
                    printer.lock();
                    try {
                        System.out.println("把文件打印处理");
                    } finally {
                        printer.unlock();
                    }
                }finally {
                    scaner.unlock();
                }

            }
        });
        thread1.start();
        thread2.start();

    }
}
