## java虚拟机



### 判断对象存活算法

##### 引用计数法

没办法处理依赖循环的问题

##### 可达性分析算法

GCRoot

### 垃圾回收算法

##### 标记清除法

会产生大量内存碎片，太多不连续的空间，会导致大对象无法有足够的空间，从而引发gc。

##### 复制算法

速度快，内存分配也不用考虑内存碎片的问题，实现简单，运行高效，但内存要减少了一半，一般在新生代使用

##### 标记整理算法

效率不高，但解决了内存碎片的问题，老年代的回收算法，这也是MajorGC时间比MinorGC长的原因

##### 分代算法

综合以上算法，把内存规划成几块，采用不同的算法回收，从而提高效率。



### 垃圾收集器

##### Serial收集器（串行收集器）

单线程的新生代回收器。

缺点：stw的时间相对比较长

优点：简单高效，在单cpu环境下，没有线程交互的开销。适合在内存占用不大的客户端上使用。

##### ParNew收集器

Serial收集器的多线程版本，和Serial共用很多代码。所以在控制参数（如：-XX:SurvivorRatio、-XX:PretenureThreshold、-XX:HandlePromotionFailure等）、收集算法、stw、对象分配规则、回收策略等都完全一样。

主要是配合CMS垃圾收集器使用：-XX:UseConcMarkSweepGC开启后，新生代默认开启（-XX:UseParNewGC）

在单CPU环境下，由于线程交互的开销，效果不会比Serial收集器效果好（即使使用超线程技术）。但多CPU效果就能很好的发挥出来了。-XX:ParallelGCThreads可以控制垃圾收集的线程数。

##### Parallel Scavenge收集器

新生代垃圾收集器，使用复制算法，并行多线程收集器。与其他垃圾收集器关注点不同的是，Parallel Scavenge垃圾收集关注的是达到一个可控制的吞吐量（运行用户代码时间/（用户代码运行时间+垃圾收集时间））

-XX:MaxGCPauseMills(单位为毫秒)、-XX:GCTimeRatio（0~100）

可以使用-XX:UseAdaptiveSizePolicy.这样就不用手动设置-Xmn、-XX:SurvivorRatio、-XX：PretenureSizeThreshold等参数了。虚拟机会根据当前系统运行情况收集性能监控信息，动态调整这些参数。达到最合适的停顿时间和最大的吞吐量。这种方式称为GC自适应调节策略。所以这种模式下，只需要设置最大堆Xmx、设置一个MaxGCPauseMills或者GCTimeRatio就可以了。

##### Serial old收集器

Serial收集器的老年代版本，单线程、使用标记整理算法。

1.5前配合Parallel使用，CMS（Concurrent Mark Sweep）收集器在发生Concurrent Mode Failure时的后备方案。

##### Parallel old 收集器

parallel Scavenge收集器的老年代版本，使用多线程、标记整理算法。1.6后提供。

##### CMS收集器

获取最短回收停顿时间为目标的垃圾收集器。

过程：

初始标记（stw），标记GC Roots能直接关联到的对象。

并发标记，进行GC Roots Tracing的过程

重新标记（stw），修正并发标记过程中

并发清理，和用户线程一起工作。

缺点：

cpu资源敏感，默认启动的线程数（CPU数量+3）/4，所以，当cpu>=4需要占用不少于25%的cpu资源。cpu<4就更多了。所以cms垃圾回收器用4核以上，才能显示出他更好的效果。

无法处理浮动垃圾。因为CMS收集器是和用户线程并行的，所以老年代还要预留一部分空间给用户线程。可以通过-XX:CMSInitiatingOccupancyFraction的值设置触发百分比。1.5：68%，1.6：92%。越大越容易Concurrent Mode Failure,这样会退化为Serial old收集，导致性能降低。

CMS基于标记-清除法，所以会产生大量的内存碎片，有几率会导致大对象存放空间不足，触发fullgc。我们可以通过设置参数-XX:CMSFullGCsBeforeCompaction,表示执行多少次不压缩的fullgc后，来一次压缩的。

##### G1收集器

还是采用分代思想，但是内存不在物理隔离了，而是分成一个一个的区域（region），可以有计划的避免java堆中进行全区域垃圾收集。

并行并发

分代收集

空间整合：整体是标记清理，局部region上是复制算法，所以运行期间不产生内存空间碎片。收集后，能提供规整的内存空间。

可预测的停顿。

RememberSet

##### ZGC收集器





### 查看当前 Java 版本默认使⽤的垃圾回收器

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



### jvm调优指标

系统容量

延迟

吞吐量

#### 选择垃圾回收器

如果使用的堆内存不是很大（比如100M），使用Serial收集器一般效率是最高的。-XX:+UseSerialGC。

如果使用的都是单核服务器，选择串行收集器依然是合适的，这时候启用一些并行收集器没有任何收益。参数:-XX:+UseSerialGC。

如果你的应用是“吞吐量”优先的，并且对较长时间的停顿没有 什么特别的要求。选择并行收集器是比较好的。参数:- XX:+UseParallelGC。

如果你的应用对响应时间要求较高，想要较少的停顿。甚至 1 秒的停顿都会引起大量的请求失败，那么选择 G1、ZGC、CMS 都是合理的。虽然这些收集器的 GC 停顿通常都比较短，但它 需要一些额外的资源去处理这些工作，通常吞吐量会低一些。 参 数 : -XX:+UseConcMarkSweepGC 、 -XX:+UseG1GC 、 - XX:+UseZGC 等。



所以平时的web服务器都是对响应性要 求非常高的。选择性其实就集中在 CMS、G1、ZGC 上。而对于某些定时任务，使用并行收集器，是一个比较好的选择。



#### 估算

假如一个接口每天有10亿次请求，每次请求的大小有 20KB，假如每次请求的大小有 20KB(很容易 达到)，那么一天的流量就有 18TB 之巨。假如高峰请求 6w/s，我们部署了10 台机器，那么每个 JVM 的流量就可以达到120MB/s，这个速度算是比较快的了。

我们的业务场景是高并发的。对象诞生的快，死亡的也快，对年轻代 的利用直接影响了整个堆的垃圾收集。

足够大的年轻代，会增加系统的吞吐，但不会增加 GC 的负 担。

容量足够的 Survivor 区，能够让对象尽可能的留在年轻代，减 少对象的晋升，进而减少 Major GC。

我们还看到了一个元空间引起的 Full GC 的过程，这在高并发的场景 下影响会格外突出，尤其是对于使用了大量动态类的应用来说。通过 调大它的初始值，可以解决这个问题。



GC日志配置



我们来盘点一下对于问题的排查，现在都有哪些资源:

- GC 日志，能够反映每次 GC 的具体状况，可根据这些信息调整 一些参数及容量;
- 问题发生点的堆快照，能够在线下找到具体内存泄漏的原因;
- 问题发生点的堆栈信息，能够定位到当前正在运行的业务，以 及一些死锁问题;
- 操作系统监控，比如 CPU 资源、内存、网络、I/O 等，能够看 到问题发生前后整个操作系统的资源状况;
- 服务监控，比如服务的访问量、响应时间等，可以评估故障堆 服务的影响面，或者找到一些突增的流量来源;
- JVM 各个区的内存变化、GC 变化、耗时等监控，能够帮我们 了解到 JVM 在整个故障周期的时间跨度上，到底发生了什么。



