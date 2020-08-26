package com.idea4j.jvm;

public class YoungToOld {

    /**
     * 新生代对象晋升到老年代的几种场景：
     *      躲过15次GC
     *      符合动态年龄判断规则
     *      Young GC后存活对象放不进Survivor
     *      大对象直接进入老年代
     * -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520
     * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC
     * -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:YoungToOld-gc.log
     */
    public static void main(String[] args) {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];

        byte[] array2 = new byte[128 * 1024];
        array2 = null;

        byte[] array3 = new byte[2 * 1024 * 1024];

        /**
         * Java HotSpot(TM) 64-Bit Server VM (25.151-b12) for windows-amd64 JRE (1.8.0_151-b12), built on Sep  5 2017 19:33:46 by "java_re" with MS VC++ 10.0 (VS2010)
         * Memory: 4k page, physical 16645092k(5067544k free), swap 32373732k(12551596k free)
         * CommandLine flags: -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:MaxNewSize=10485760 -XX:MaxTenuringThreshold=15 -XX:NewSize=10485760 -XX:OldPLABSize=16 -XX:PretenureSizeThreshold=10485760 -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC
         * 0.260: [GC (Allocation Failure) 0.260: [ParNew: 6297K->674K(9216K), 0.0022330 secs] 6297K->2724K(19456K), 0.0024932 secs] [Times: user=0.08 sys=0.02, real=0.00 secs]
         * Heap
         *  par new generation   total 9216K, used 5220K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         *   eden space 8192K,  55% used [0x00000000fec00000, 0x00000000ff070688, 0x00000000ff400000)
         *   from space 1024K,  65% used [0x00000000ff500000, 0x00000000ff5a8b68, 0x00000000ff600000)
         *   to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
         *  concurrent mark-sweep generation total 10240K, used 2050K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         *  Metaspace       used 3473K, capacity 4496K, committed 4864K, reserved 1056768K
         *   class space    used 381K, capacity 388K, committed 512K, reserved 1048576K
         */
    }
}
