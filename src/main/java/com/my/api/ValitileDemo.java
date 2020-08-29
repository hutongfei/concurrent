package com.my.api;

public class ValitileDemo {

    static volatile int state = 0;
//    static  int state = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = 1;
            System.out.println("I has changed current state !");
        },"Thread - 1").start();

        new Thread(() -> {
            System.out.println("is begin !");
            while (state == 0) {
            }
            System.out.println("current state is not 0  !");
        },"Thread - 2").start();

    }
}
