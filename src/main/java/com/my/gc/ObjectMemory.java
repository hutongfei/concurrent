package com.my.gc;

import org.openjdk.jol.info.ClassLayout;

/**
 * 关闭指针压缩
 */
public class ObjectMemory {
    public static void main(String[] args) {
        O o = new O();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        /**
         * 关闭指针压缩 开启指针压缩 -XX:-UseCompressedOops 对象布局如下
         *         OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *         0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *         4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *         8     4        (object header)                           30 35 5c 20 (00110000 00110101 01011100 00100000) (542913840)
         *         12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *         16     1   byte O.b                                       0
         *         17     7        (loss due to the next object alignment)
         **/
        /**
         * 开启指针压缩（默认） 对象布局如下
         *         OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *         0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *         4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *         8     4        (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
         *         12     1   byte O.b                                       0
         *         13     3        (loss due to the next object alignment)
         */

    }
}
