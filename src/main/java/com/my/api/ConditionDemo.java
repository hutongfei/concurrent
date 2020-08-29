package com.my.api;


import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final Condition condition = lock.newCondition();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName());
                    condition.signal();
                    condition.await();
                    lock.unlock();
                }
            }
        },"Thread - 1").start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName());
                    condition.signal();
                    condition.await();
                    lock.unlock();
                }
            }
        },"Thread - 2").start();

    }
}
