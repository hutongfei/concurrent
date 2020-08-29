package com.my.utils;

import java.util.Date;
import java.util.concurrent.Exchanger;

/**
 * Exchanger（交换者）是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交
 * 换。它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。这两个线程通过
 * exchange方法交换数据，如果第一个线程先执行exchange()方法，它会一直等待第二个线程也
 * 执行exchange方法，当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产
 * 出来的数据传递给对方。
 */
public class ExchangerDemo {

    static Exchanger<Object> threadExchange = new Exchanger<>();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                System.out.println("A 录入的流水" + "A");
                Object fromBInfo = threadExchange.exchange("B");
                System.out.println("A 中 交换后流水"+ fromBInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread - 1").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                System.out.println("B 录入的流水" + "B");
                Object fromAInfo = threadExchange.exchange("A");
                System.out.println("B 中 交换后流水"+ fromAInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread - 2").start();
    }
}

/**
 * 如果两个线程有一个没有执行exchange()方法，则会一直等待，如果担心有特殊情况发
 * 生，避免一直等待，可以使用exchange（V x，longtimeout，TimeUnit unit）设置最大等待时长。
 */
