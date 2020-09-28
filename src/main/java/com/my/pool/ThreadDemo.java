package com.my.pool;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * 总线程数（排队线程数 + 活动线程数 +  执行完成线程数）：
 */
public class ThreadDemo {
     static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 15, 1,TimeUnit.MINUTES,  new ArrayBlockingQueue<>(2));
    public static void main(String[] args) {

        Runnable runnable = ()->{
            System.out.println("");
        };
        PoolTread poolTread = new PoolTread();
        for (int i = 0; i < 108; i++) {
            threadPoolExecutor.submit(poolTread);
            System.out.print( "   total   " + threadPoolExecutor.getTaskCount() + "  " + "active  " + threadPoolExecutor.getActiveCount() + " complete " + threadPoolExecutor.getCompletedTaskCount() + " wait   "+ threadPoolExecutor.getQueue().size() );
            System.out.println("");

        }
    }
}

class PoolTread implements Runnable{
    static int count = 200;
    public PoolTread() {
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000);
        --count;
        System.out.println( Thread.currentThread().getName());
//        System.out.print((-- count) + "  " + Thread.currentThread().getName());
    }
}
