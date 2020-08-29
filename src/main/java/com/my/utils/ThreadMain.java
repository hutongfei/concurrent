package com.my.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadMain {

    static int count = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Object> task = new FutureTask<Object>(() -> {
            count ++;
            return Thread.currentThread().getName() + "   " + count;
        });

        new Thread(task,"Thread - 1").start();
        System.out.println(task.get());


    }
}

//class CallImpl implements Callable<Object> {
//
//    static int count = 0;
//
//    @Override
//    public Object call() throws Exception {
//        count ++;
//        return count;
//    }
//}
