package com.my.utils;

import java.util.concurrent.locks.LockSupport;

public class SortThread {

    static Thread t1 = null;

    static Thread t2 = null;

    static Thread t3 = null;

    public static void main(String[] args) {

         t3 = new Thread(() -> {
             LockSupport.park(Thread.currentThread());
            System.out.println(Thread.currentThread().getName() + " last execute me !");
        }, "Thread - 3");

         t2 =  new Thread(() -> {
             LockSupport.park(Thread.currentThread());
             System.out.println(Thread.currentThread().getName() + " second execute me !");
             LockSupport.unpark(t3);
        }, "Thread - 2");

         t1 = new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + " fist execute me !");
             LockSupport.unpark(t2);
        }, "Thread - 1");
//        LockSupport.unpark(t1);
        t3.start();
        t2.start();
        t1.start();


    }
}
