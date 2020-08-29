package com.my.pool;

import java.util.concurrent.*;

public class ThreadPool {

//    final static Object object = new Object();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BlockingQueue<Runnable> threads = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 15, 1, TimeUnit.MINUTES, threads);

        // 提交不需要返回值的任务
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        });

        // 提交需要返回值的任务

        Callable<Object> callable = () -> {
            return "this is callable return value ";
        };
        Future<Object> submit = threadPoolExecutor.submit(callable);
        System.out.println(submit.get());

    }
}

/**
 *      ThreadPoolExecutor执行execute方法
 *      1）如果当前运行的线程少于corePoolSize，则创建新线程来执行任务（注意，执行这一步骤需要获取全局锁）。
 *      2）如果运行的线程等于或多于corePoolSize，则将任务加入BlockingQueue。
 *      3）如果无法将任务加入BlockingQueue（队列已满），则创建新的线程来处理任务（注意，执行这一步骤需要获取全局锁）。
 */
