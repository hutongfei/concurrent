package com.my.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CasLock implements Lock {

    private volatile long state = 0;

    private static Unsafe unsafe;
    private static long valueOffset;

    static {
        try {
            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            unsafe = (Unsafe) singleoneInstanceField.get(null);
            valueOffset = unsafe.objectFieldOffset(CasLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean lock() {
        return unsafe.compareAndSwapInt(this, valueOffset, 0, 1);
    }

    @Override
    public void unLock() {
       this.state = 0;
    }


    static int count = 0;

    public static void main(String[] args) {

        CasLock casLock = new CasLock();

        for (int j = 0; j < 100; j++) {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    while (!casLock.lock()) {
                        System.out.println(Thread.currentThread().getName() + " yielding");
                        Thread.yield();
                    }
//                    System.out.println(Thread.currentThread().getName() + "running");
                    count++;
                    casLock.unLock();
                }
            }).start();
        }


        try {
            Thread.sleep(2000);
            System.out.println("值为 -> " + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class LockThread implements Runnable {

    static int count = 10;

    private CasLock casLock;

    public LockThread(CasLock casLock) {
        this.casLock = casLock;
    }

    @Override
    public void run() {
        casLock.lock();
        count--;
        System.out.println(count);
        casLock.unLock();
    }
}
