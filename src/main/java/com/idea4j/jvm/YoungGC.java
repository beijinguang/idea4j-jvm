package com.idea4j.jvm;

public class YoungGC {
    /**
     * -XX:NewSize=5242880 -XX:MaxNewSize=5242880 -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760
     * -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
     * @param args
     */
    public static void main(String[] args) {
        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];

        /**
         * Java HotSpot(TM) 64-Bit Server VM (25.151-b12) for windows-amd64 JRE (1.8.0_151-b12), built on Sep  5 2017 19:33:46 by "java_re" with MS VC++ 10.0 (VS2010)
         * Memory: 4k page, physical 16645092k(5262368k free), swap 32373732k(12801116k free)
         * CommandLine flags: -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:MaxNewSize=5242880 -XX:NewSize=5242880 -XX:OldPLABSize=16 -XX:PretenureSizeThreshold=10485760 -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC
         * 0.237: [GC (Allocation Failure) 0.237: [ParNew: 3122K->511K(4608K), 0.0022167 secs] 3122K->1684K(9728K), 0.0023794 secs] [Times: user=0.06 sys=0.00, real=0.00 secs]
         * 0.240: [GC (Allocation Failure) 0.240: [ParNew: 2678K->241K(4608K), 0.0019574 secs] 3851K->1923K(9728K), 0.0020099 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * Heap
         *  par new generation   total 4608K, used 2359K [0x00000000ff600000, 0x00000000ffb00000, 0x00000000ffb00000)
         *   eden space 4096K,  51% used [0x00000000ff600000, 0x00000000ff811958, 0x00000000ffa00000)
         *   from space 512K,  47% used [0x00000000ffa00000, 0x00000000ffa3c5c8, 0x00000000ffa80000)
         *   to   space 512K,   0% used [0x00000000ffa80000, 0x00000000ffa80000, 0x00000000ffb00000)
         *  concurrent mark-sweep generation total 5120K, used 1682K [0x00000000ffb00000, 0x0000000100000000, 0x0000000100000000)
         *  Metaspace       used 3459K, capacity 4496K, committed 4864K, reserved 1056768K
         *   class space    used 379K, capacity 388K, committed 512K, reserved 1048576K
         *
         *  （1）.GC情况概览
         *      0.237: [GC (Allocation Failure) 0.237: [ParNew: 3122K->511K(4608K), 0.0022167 secs] 3122K->1684K(9728K), 0.0023794 secs] [Times: user=0.06 sys=0.00, real=0.00 secs]
         *      0.237系统运行了0.237秒以后，发生了本次GC；
         *      GC (Allocation Failure) ：说明了为啥发生GC，因为对象分配失败，也就是上述的Eden区空间不足了；
         *      [ParNew: 3122K->511K(4608K), 0.0022167 secs]：使用ParNew进行新生代的GC，GC前新生代使用了3122K，GC完成后新生代使用了511K，4608K表示年轻代的总空间（Eden+1个Survivor），本次GC耗时0.0022167秒；
         *      3122K->1684K(9728K), 0.0023794 secs：Java堆内存的总空间为9728K，GC前使用了3122K，GC后使用了1684K；
         *
         *   (2).JVM退出时堆内存
         *      par new generation   total 4608K, used 2359K：ParNew负责的新生代总共有4608K内存，目前使用了2359K
         *      eden space 4096K,  51% used：Eden总共使用了4096K
         *      from space 512K,  47% used： From Survivor区使用了47%（存放转移过来的未知存活对象）
         *      to   space 512K,   0% used：To Survivor区未使用
         *
         *      concurrent mark-sweep generation total 5120K, used 1682K:使用CMS管理的老年代总空间为5120K，已使用1682K
         *      Metaspace       used 3459K, capacity 4496K, committed 4864K, reserved 1056768K：元数据区的空间信息
         *      class space    used 379K, capacity 388K, committed 512K, reserved 1048576K：Class空间信息
         *
         */
    }
}
