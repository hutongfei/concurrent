package com.my.fork;

import java.util.concurrent.*;

public class FatureTaskDemo {

    public static void main(String[] args)throws Exception {

        // n多个线程执行任务，必须等其中一个线程执行完才可以开始执行
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 15, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

        FutureTask<Object> futureTask = new FutureTask<>(() -> {
//            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " 000000000000000000000");
            return "Thread";
        });

        new Thread(futureTask).start();

        if (futureTask != null) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "");
            },"Thread  -   1").start();
        }
        System.out.println(Thread.currentThread().getName() + "is finished!");
    }
}
