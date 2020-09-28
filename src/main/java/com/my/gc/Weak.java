package com.my.gc;

import java.lang.ref.WeakReference;

/**
 * 只有触发gc 就回收弱引用
 */
public class Weak {

    static WeakReference weakReference = new WeakReference<>(new byte[1024*1024*12]);

    public static void main(String[] args) throws InterruptedException {
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

    }

}
