package com.my.fork;

public class Demo {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.print("1");
                Thread.yield();
                System.out.print("2");
            },"Thread - 1").start();
        }
        // 12 12  1122

    }
}
