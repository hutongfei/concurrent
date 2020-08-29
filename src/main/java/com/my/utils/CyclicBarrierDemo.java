package com.my.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier默认的构造方法是CyclicBarrier（int parties），其参数表示屏障拦截的线程数
 * 量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
 * 　同步屏障CyclicBarrier
 */
public class CyclicBarrierDemo {

    static Thread firstThread = null;

    static CyclicBarrier cyclicBarrier = null;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        Thread.currentThread().setName("Thread - main");
        /**
         * 在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
         */
        cyclicBarrier =  new CyclicBarrier(2,new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 优先执行 我");
        },"Thread - first "));

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "达到屏障之前");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"Thread - 1").start();

        System.out.println(Thread.currentThread().getName() + "达到屏障之前");
        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName());
    }

    /**
     * CyclicBarrier可以用于多线程计算数据，最后合并计算结果的场景。例如，用一个Excel保
     * 存了用户所有银行流水，每个Sheet保存一个账户近一年的每笔银行流水，现在需要统计用户
     * 的日均银行流水，先用多线程处理每个sheet里的银行流水，都执行完之后，得到每个sheet的日
     * 均银行流水，最后，再用barrierAction用这些线程的计算结果，计算出整个Excel的日均银行流
     */


    public static void cyCMethod() {

    }


}
