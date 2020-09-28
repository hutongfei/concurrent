package com.my.gc;

import java.lang.ref.SoftReference;

/**
 * 软引用
 * 内存够用，不回收软引用
 * 只要强引用 不够用内存，直接回收软引用
 */
public class Soft {
    static SoftReference softReference = new SoftReference<>(new byte[1024*1024*12]);

    public static void main(String[] args) {
        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get());

        byte[] bytes = new byte[1024 * 1024 * 12 ];
        System.out.println(bytes);
        System.out.println(softReference.get());

    }
}
