package org.example.yugong.base;

import java.util.stream.LongStream;

/**
 * @author qiaobao
 * @since 2025/5/20
 */
public class SteamTest {

    public static void main(String[] args) {
        //    测试一下steam流的并行计算能力
        parallelStream();
        serialStream();
    }

    private static void serialStream() {
        // 测试一下steam流的并行计算能力
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10000000000L)
                .reduce(0, Long::sum);  // 并行计算
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum);
        System.out.println("time = " + (end - start));
    }

    public static void parallelStream() {
        // 测试一下steam流的并行计算能力
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10000000000L)
                .parallel()
                .reduce(0, Long::sum);  // 并行计算
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum);
        System.out.println("time = " + (end - start));
    }
}
