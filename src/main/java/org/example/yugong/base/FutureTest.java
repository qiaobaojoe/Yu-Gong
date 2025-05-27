package org.example.yugong.base;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author qiaobao
 * @since 2025/5/20
 */
public class FutureTest {
    public static void main(String[] args) {
        // future 异步编程的测试代码
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "World";
        });

        // 组合两个Future的结果
        CompletableFuture<String> combinedFuture = future1
            .thenCombine(future2, (result1, result2) -> result1 + " " + result2)
            .exceptionally(throwable -> "Error: " + throwable.getMessage());

        // 添加完成后的回调
        combinedFuture.thenAccept(result -> System.out.println("Combined result: " + result));

        // 等待执行完成
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test();
    }

    public static void test() {
        // future 异步编程的测试代码
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // 模拟耗时计算
                Thread.sleep(2000);
                return 123;
            }
        });

        try {
            // 等待计算结果，最多等待3秒
            Integer result = future.get(3, TimeUnit.SECONDS);
            System.out.println("计算结果: " + result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
