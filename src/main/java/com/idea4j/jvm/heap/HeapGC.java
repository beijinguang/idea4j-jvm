package com.idea4j.jvm.heap;

/**
 * -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xms40M -Xmx40M -Xmn20M
 * Xmx 堆内存大小
 * Xmn 新生代大小
 * SurvivorRatio  eden：from：to=8：1：1
 * 所以，本程序堆内存分布为   新生代：20M（eden：16M，from：2M，to：2M），老年代：20M
 */
public class HeapGC {
    public static void main(String[] args){
        /**
         * 分配一块0.2M的内存，优先放在新生代eden中，此时，eden中占有0.2M
         */
        byte[] b1 = new byte[1024 * 1024 /5];

        /**
         * 分配一块8M的内存，优先放在新生代eden中，此时，eden中占有8M
         */
        byte[] b2 = new byte[1024 * 1024 *8];

        /**
         * 分配一块7M的内存,发现eden空间不足，触发youngGC，b1进入from中（survivor），
         * b2由于survivor放不下，直接进入老年代，eden空间足了，b3存入eden
         * 此时，eden：7M，survivor：0.2，老年代：8M
         */
        byte[] b3 = new byte[1024 * 1024 *7];

        b2 = null;

        /**
         * 分配一块8M的内存,eden空间能放下
         * 此时，eden：7M+8M(没有引用，可回收)，survivor：0.2，老年代：8M
         */
        b2 = new byte[1024 * 1024 * 8];

        /**
         * 分配一块8M的内存,eden空间放不下直接进入老年代
         * 此时，eden：7M+8M(没有引用，可回收)，survivor：0.2，老年代：8M(没有引用，可回收)+8M
         */
        b2 = new byte[1024 * 1024 * 8];

        /**
         * 分配一块8M的内存,eden空间放不下，老年代也放不下，fullGC 之前先youngGC
         * youngGC期间，eden中8M被回收
         * 老年代GC期间，老年代中有8M被回收，新生代中的7M+0.2M进入老年代
         * 回收结束后，eden放入新分配的8M
         * 此时,eden：8M，survivor：0.2，老年代：8M(没有引用，可回收)+7M+0.2M
         */
        b2 = new byte[1024 * 1024 * 8];


        b2 = new byte[1024 * 1024 * 6];

        b2 = new byte[1024 * 1024 * 2];

//        b2 = new byte[1024 * 1024 * 8];

//        System.gc();


    }
}
