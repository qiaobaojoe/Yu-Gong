package org.example.yugong.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qiaobao
 * @since 2020-12-04
 */
public class ThreadLocalTest {


    public static  final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        System.out.println("start----->");
        inheritableThreadLocal.set("Inheritable hello");
        threadLocal.set("hello");

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                10,
                100,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1000));

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("子线程可继承值：%s",inheritableThreadLocal.get()));
                System.out.println(String.format("子线程值：%s",threadLocal.get()));

                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(String.format("孙子线程可继承值：%s",inheritableThreadLocal.get()));
                        System.out.println(String.format("孙子线程值：%s",threadLocal.get()));

                    }
                });
            }
        });
        threadLocal.remove();
        System.out.println("end ------>");


    }




}
