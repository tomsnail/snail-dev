package cn.tomsnail.snail.core.service.spring.core.async;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@PropertySource("classpath:config.properties")
public class AutoAsyncConfigurer implements AsyncConfigurer {



    public Executor getAsyncExecutor() {
        // 定义线程池
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("system.spring.async.executor.core.size","10")));
        // 线程池最大线程数
        taskExecutor.setMaxPoolSize(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("system.spring.async.executor.max.size","30")));
        // 线程队列最大线程数
        taskExecutor.setQueueCapacity(Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("system.spring.async.executor.queue.capacity","1000")));
        // 初始化
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * The {@link AsyncUncaughtExceptionHandler} instance to be used
     * when an exception is thrown during an asynchronous method execution
     * with {@code void} return type.
     */
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
