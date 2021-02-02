package org.example.yugong.interview;

import sun.jvm.hotspot.debugger.linux.LinuxDebugger;

import static java.lang.Thread.sleep;

/**
 * 题目2：
 * 你临时接到一个非常紧急的需求，需要通过一个分布式队列来实现业务逻辑，且业务要求今天就要上线。但是现有的应用并没有现成的分布式队列中间件的接入，
 * 于是你想到了用缓存来提供分布式队列的能力。
 * 请用java实现一个基于memcache的队列，支持在分布式应用场景下使用
 * 注意：
 * 1.因为是分布式应用场景，请考虑最小开销下确保队列的FIFO
 * 2.memcache对象可以自由设置其需要的方法（不必实现，前提是现有memcache开源项目能够支持的方法）。
 * 提示：代码不必写完整，只需实现offer与poll接口,能正确表达设计思路即可
 * <p>
 * 答案：
 * 假设memcache的数据都是离散的，使用key+size来保证有序，不断offer() key1,key2,key3。。
 * 那么只需要维护一个指针来标识当前队列的队头，poll的时候移动到下一个指针位置，就可以按FIFO来获取
 * 使用分布式锁来保证key+size 和 point的并发线程安全
 *
 * @author qiaobao
 * @since 2021-02-01
 */
public class ImplementDistributeQueue {

    public static final String SPIT = "-";
    public final String queueName;

    public final String firstNodeName;

    public final String lastNodeName;

    public final String lock;

    private MemcachedConnection memcachedConnection;

    public ImplementDistributeQueue(String queueName) {
        this.queueName = queueName;
        this.lock = queueName + "-lock";
        this.firstNodeName = queueName + "-first";
        this.lastNodeName = queueName + "-last";
        MemcachedCASResult lockValue = memcachedConnection.gets(this.lock);
        if (lockValue == null) {
//          初始化 队列操作锁标志，在系统中初始化存在风险，最好启动手动配置
            memcachedConnection.set(lock, 0);
        }
    }


    public void offer(Object value) throws InterruptedException {

        Boolean lock = getLock();
        if (lock) {
            try {
                String lastNode = (String) memcachedConnection.get(lastNodeName);
                String key;
                if (lastNode == null) {
                    key = queueName.concat("-1");
                    memcachedConnection.set(firstNodeName, key);
                    memcachedConnection.set(lastNodeName, key);
                } else {
                    String[] split = lastNode.split(SPIT);
                    long index = Long.parseLong(split[1]);
                    index++;
                    key = queueName.concat(SPIT).concat(String.valueOf(index));
                    memcachedConnection.replace(lastNodeName, key);
                }
                memcachedConnection.set(queueName.concat(SPIT).concat(key), value);
            } finally {
                releaseLock();
            }
        }
    }

    public Object poll() throws InterruptedException {
        Boolean lock = getLock();
        if (lock) {
            try {
                String firstNode = (String) memcachedConnection.get(firstNodeName);
                String key;
                if (firstNode == null) {
                    return null;
                } else {
                    Object returnVal = memcachedConnection.get(firstNode);
                    memcachedConnection.del(firstNode);
                    String lastNode = (String) memcachedConnection.get(lastNodeName);
                    if (lastNode.equals(firstNode)) {
                        memcachedConnection.del(firstNode);
                        memcachedConnection.del(lastNode);
                    } else {
                        String[] split = firstNode.split(SPIT);
                        long index = Long.parseLong(split[1]);
                        index++;
                        key = queueName.concat(SPIT).concat(String.valueOf(index));
                        memcachedConnection.replace(firstNode, key);
                    }

                    return returnVal;
                }

            } finally {
                releaseLock();
            }
        }

        return null;
    }

    private Boolean getLock() throws InterruptedException {
        while (true) {
            int time = 0;
            MemcachedCASResult result = memcachedConnection.gets(lock);
            int lockResult = (int) result.value;
            if (lockResult == 1) {
                sleep(10);
            }
            if (lockResult == 0) {
                Boolean cas = memcachedConnection.cas(lock, 1, result.token);
                if (cas) {
                    sleep(10);
                } else {
                    return true;
                }
            }
            time++;
            if (time > 4) {
                return false;
            }
        }
    }

    private void releaseLock() {
        MemcachedCASResult result = memcachedConnection.gets(lock);
        memcachedConnection.cas(lock, 0, result.token);
    }


    interface MemcachedConnection {

        void set(String key, Object value);

        Object get(String key);

        void replace(String key, Object val);

        void del(String key);

        Boolean cas(String key, Object value, Long token);

        //        获取cas操作的token
        MemcachedCASResult gets(String key);
    }

    class MemcachedCASResult {
        Object value;
        Long token;
    }


}
