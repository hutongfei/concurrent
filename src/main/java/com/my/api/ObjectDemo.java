package com.my.api;


import lombok.SneakyThrows;

public class ObjectDemo {
    public static void main(String[] args) {

        final Object object = new Object();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    synchronized (object) {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        object.notify();
                        object.wait();
                    }
                }
            }
        }, "Thread - 1").start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    synchronized (object) {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        object.notify();
                        object.wait();
                    }
                }
            }
        }, "Thread - 2").start();
    }
}
