package com.idea4j.jvm.jmm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class JMMTest {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static volatile int i = 0;
    public static AtomicInteger j = new AtomicInteger(0);

    public static void add(){
        i++;
        j.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 2000; j++) {
           executorService.execute(new Runnable() {
               public void run() {
                   add();
               }
           });
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println(i);
        System.out.println(j.get());

    }
}
