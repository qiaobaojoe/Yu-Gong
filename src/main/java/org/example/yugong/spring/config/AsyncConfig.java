package org.example.yugong.spring.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author qiaobao
 * @since 2021-03-05
 */

@Configuration
@EnableAsync
public class AsyncConfig {


    @Bean("userExecutorsPool")
    public Executor getExecutors() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("consumer-queue-thread-%d").build();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 线程池维护线程的最少数量
        executor.setCorePoolSize(5);
        // 线程池维护线程的最大数量
        executor.setMaxPoolSize(10);
        // 缓存队列
        executor.setQueueCapacity(25);
        //线程名
        executor.setThreadFactory(namedThreadFactory);
        // 线程池初始化
        executor.initialize();
        return executor;

    }
}
