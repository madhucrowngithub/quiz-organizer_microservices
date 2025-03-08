package com.quiz.question_services.configuration.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorConfiguration {
    @Bean(name = "taskExecutor", destroyMethod="shutdown")
    public ExecutorService taskExecutor(){
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean(name = "asyncTaskExecutor")
    public AsyncTaskExecutor applicationTaskExecutor() {
        return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
    }
}
