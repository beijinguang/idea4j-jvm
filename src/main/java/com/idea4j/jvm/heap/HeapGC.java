package com.idea4j.jvm.heap;

/**
 * -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xms40M -Xmx40M -Xmn20M
 * Xmn 新生代大小
 */
public class HeapGC {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        byte[] b1 = new byte[1024 * 1024 /5];
        byte[] b2 = new byte[1024 * 1024 *8];
        b2 = null;
        b2 = new byte[1024 * 1024 * 8];
        System.gc();
//        System.gc();
//        System.gc();
//        System.gc();
        Thread.sleep(100000);
    }
}
