package org.example.yugong.thread;

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
        new Thread(()->{
            System.out.println(String.format("子线程可继承值：%s",inheritableThreadLocal.get()));
            System.out.println(String.format("子线程值：%s",threadLocal.get()));
            new Thread(()->{
                System.out.println(String.format("孙子线程可继承值：%s",inheritableThreadLocal.get()));
                System.out.println(String.format("孙子线程值：%s",threadLocal.get()));
            }).start();

        }).start();

        threadLocal.remove();
        System.out.println("end ------>");




    }




}
