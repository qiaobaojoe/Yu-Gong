package org.example.yugong.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qiaobao
 * @since 2021-01-13
 */
public class ConcurrentCache {

    public static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();

    public static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);

    public static boolean put(Object key, Object value, int time, TimeUnit timeUnit) {

        if (concurrentHashMap.putIfAbsent(key, value) != null) {
            return false;
        }

        String format = String.format("key=%s,value=%s,expireTime=%s", key, value,time+timeUnit.toString());
        System.out.println("放入缓存成功：" + format);
        scheduledThreadPoolExecutor.schedule(new CleanCache(key, value), time, timeUnit);
        return true;
    }

    static class CleanCache implements Runnable {

         Object key;

         Object value;

        CleanCache(Object key, Object value) {
            this.key = key;
            this.value = value;
        }


        @Override
        public void run() {
            boolean remove = concurrentHashMap.remove(key, value);
            String format = String.format("key=%s,value=%s", key, value);
            if (remove) {
                System.out.println("清除过期缓存成功："+format);
            } else {
                System.out.println("缓存不存在：" + format);
            }
        }


    }

    public static void main(String[] args) {

        ConcurrentCache.put(1, "test1", 1, TimeUnit.SECONDS);
        ConcurrentCache.put(2, "test2", 2, TimeUnit.SECONDS);
        ConcurrentCache.put(3, "test3", 3, TimeUnit.SECONDS);

    }



}
