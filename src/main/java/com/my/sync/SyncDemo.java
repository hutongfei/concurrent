package com.my.sync;


import org.openjdk.jol.info.ClassLayout;

/**
 * Java对象的对象头由 mark word 和  klass pointer 两部分组成，
 * mark word存储了同步状态、标识、hashcode、GC状态等等。
 * klass pointer存储对象的类型指针，该指针指向它的类元数据
 * https://www.cnblogs.com/LemonFive/p/11246086.html
 */
public class SyncDemo {

    public static void main(String[] args) {

        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        unused:1 | age:4 | biased_lock:1 | lock:2
//         01 00 00 00 (00000001 00000000 00000000 00000000) (1)


    }
}
