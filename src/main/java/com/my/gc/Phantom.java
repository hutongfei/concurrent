package com.my.gc;

import java.io.Serializable;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 配置 -Xmx20m 不然没效果
 */
public class Phantom implements Serializable {

    public static void main(String[] args) {

        ReferenceQueue<Object> refQueue = new ReferenceQueue();

        PhantomReference<Object> phantomReference = new PhantomReference<Object>( new Object(), refQueue);

        List<byte[]> bytes = new ArrayList<>();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                bytes.add(new byte[1024 * 1024]);
//                System.gc();
            }
        }).start();

        new Thread(() -> {
            while (true){
                Reference<?> poll = refQueue.poll();
                if (poll != null) {
                    System.out.println(poll);
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("回收啦");
    }
}
