可以查看当前 Java 版本默认使⽤的垃圾回收器
java  -XX:+PrintCommandLineFlags  -version

-XX:G1ConcRefinementThreads=8 
-XX:GCDrainStackTargetSize=64 
-XX:InitialHeapSize=266321472 
-XX:MaxHeapSize=4261143552 
-XX:MinHeapSize=6815736 
-XX:+PrintCommandLineFlags 
-XX:ReservedCodeCacheSize=251658240 
-XX:+SegmentedCodeCache 
-XX:+UseCompressedClassPointers 
-XX:+UseCompressedOops 
-XX:+UseG1GC 
-XX:-UseLargePagesIndividualAllocation
java version "14.0.2" 2020-07-14
Java(TM) SE Runtime Environment (build 14.0.2+12-46)
Java HotSpot(TM) 64-Bit Server VM (build 14.0.2+12-46, mixed mode, sharing)



-XX:InitialHeapSize=266321472 
-XX:MaxHeapSize=4261143552 
-XX:+PrintCommandLineFlags 
-XX:+UseCompressedClassPointers 
-XX:+UseCompressedOops 
-XX:-UseLargePagesIndividualAllocation 
-XX:+UseParallelGC

java version "1.8.0_151"
Java(TM) SE Runtime Environment (build 1.8.0_151-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.151-b12, mixed mode)



- -XX:+UseSerialGC 年轻代和⽼年代都⽤串⾏收集器 
- -XX:+UseParNewGC 年轻代使⽤ ParNew，⽼年代使⽤ Serial Old
- -XX:+UseParallelGC 年轻代使⽤ ParallerGC，⽼年代使⽤ Serial Old
- -XX:+UseParallelOldGC 新⽣代和⽼年代都使⽤并⾏收集器 
- -XX:+UseConcMarkSweepGC，表⽰年轻代使⽤ ParNew，⽼年 代的⽤ CMS 
- -XX:+UseG1GC 使⽤ G1垃圾回收器 
- -XX:+UseZGC 使⽤ ZGC 垃圾回收器

