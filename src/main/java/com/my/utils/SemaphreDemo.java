package com.my.utils;

import java.util.concurrent.Semaphore;

/**
 * Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以
 * 保证合理的使用公共资源。
 */
public class SemaphreDemo {

    static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " can fixed!");
                System.out.println(semaphore.availablePermits() + " can permitCount");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread - 1").start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "  can fixed!");
                System.out.println(semaphore.availablePermits() + " can permitCount");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread - 2").start();

//        Thread.sleep(1000);
        semaphore.acquire();
        System.out.println(semaphore.availablePermits() + " can permitCount");
        System.out.println(Thread.currentThread().getName() + "  can fixed!");

    }

    /**
     * Semaphore还提供一些其他方法，具体如下。
     * ·intavailablePermits()：返回此信号量中当前可用的许可证数。
     * ·intgetQueueLength()：返回正在等待获取许可证的线程数。
     * ·booleanhasQueuedThreads()：是否有线程正在等待获取许可证。
     * ·void reducePermits（int reduction）：减少reduction个许可证，是个protected方法。
     * ·Collection getQueuedThreads()：返回所有等待获取许可证的线程集合，是个protected方
     */
}
