package com.my.gc;

import java.io.IOException;
import java.lang.ref.SoftReference;

public class Demo {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    static byte[] bs = new byte[100 * 10];

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            threadLocal.set("connection");
            t1();
        },"thread1").start();
    }


    public static void t1() {
        String t0 = threadLocal.get();
        System.out.println(t0);
        System.out.println(Thread.currentThread().getName() + "      t1" );
        t2();
    }

    public static void t2() {
        String t0 = threadLocal.get();
        System.out.println(t0);
        System.out.println(Thread.currentThread().getName() + "     t2" );
    }
}

