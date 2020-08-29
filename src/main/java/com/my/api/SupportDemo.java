package com.my.api;

import java.util.concurrent.locks.LockSupport;

public class SupportDemo {
    private static Thread t1 = null;

    private static Thread t2 = null;

    public static void main(String[] args) {

        t1 =  new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() );
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        },"Thread - 1");

        t2 =  new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() );
                LockSupport.unpark(t1);
                LockSupport.park();
            }

        },"Thread - 2");

        t1.start();
        t2.start();



    }
}
