package com.my.sync;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockQueue {
    //队列容器
    private List<Integer> container = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    //Condition
    //  队列为空
    private Condition isNull = lock.newCondition();
    // 队列已满
    private Condition isFull = lock.newCondition();
    private volatile int size = 0;
    private volatile int capacity;

    public MyBlockQueue(int capacity) {
        this.capacity = capacity;

    }

    public void add(Integer element) throws InterruptedException {
        lock.lock();
        while (isFull()) { // 队列满时，写入线程被阻塞
            System.out.println(Thread.currentThread().getName() + "  空， 被阻塞 !");
            isFull.await();
        }
        System.out.println(Thread.currentThread().getName() + " 非空， add ");
        container.add(element);
         ++ size;
        System.out.println(Thread.currentThread().getName() + "     添加后size=      " +  size);
        isNull.signal();
        lock.unlock();
    }

    public boolean isFull() {
        return size == capacity - 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public Integer get() throws InterruptedException {
        lock.lock();
        while (isEmpty()) { // 队列空时，读取线程被阻塞
            System.out.println(Thread.currentThread().getName() + "  空，   被阻塞 !");
            isNull.await();
        }
        System.out.println(Thread.currentThread().getName() + " 非空， get ");
        container.get(0);
        container.remove(0);
        --size;
//        System.out.println(Thread.currentThread().getName() + "     减少后      " +  size);
        isFull.signalAll();
        lock.unlock();
        return null;
    }

    static CountDownLatch countDownLatch = new CountDownLatch(40);

    public static void main(String[] args) throws InterruptedException {
        MyBlockQueue myBlockQueue = new MyBlockQueue(20);
        ExecutorService putThread = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                    myBlockQueue.add(new Random(10).nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"putThread"+i).start();
        }

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    myBlockQueue.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"getThread"+i).start();
        }


        Thread.sleep(3000);
        System.out.println("当前队列大小    为" +  myBlockQueue.size);




    }
}
