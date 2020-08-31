package com.idea4j.jvm;

import java.util.Vector;

/**
 *
 */
public class JVMParameters {
    public static void main(String[] args) {
        test4();
    }

    /**
     * 设置线程栈
     * -Xss
     */
    private static void test4() {
        int i = 0;
        try {
            for (i = 0; i < 1000000; i++) {
                new MyThread().start();
            }
            System.out.println("end");
        } catch (OutOfMemoryError error) {

        }finally {
            System.out.println("count thread is"+i);
        }

    }

    /**
     * 设置新生代（一般设置为整个堆内存的1/3或者1/4）
     * -Xmn   等同于（-XX：NewSize  -XX：MaxNewSize）
     * -Xmx11M -Xms11M -Xmn3M -XX:+PrintGCDetails
     */
    private static void test3() {
        Vector v = new Vector();
        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1024*1024];
            v.add(b);
            if (3==v.size()) {
                v.clear();
            }
        }
    }

    /**
     * 设置最小堆内存
     * -Xms4M
     * -Xmx11M -Xms4M -verbose:gc
     */
    private static void test2() {
        Vector v = new Vector();
        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1024*1024];
            v.add(b);
            if (3==v.size()) {
                v.clear();
            }
        }
    }

    /**
     * 设置堆最大内存
     * -Xmx11M
     */
    private static void test1() {
        Vector vector = new Vector();
        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1024 * 1024];
            vector.add(b);
            System.out.println(i + "M is allocted");
        }
        System.out.println("Max memory"+Runtime.getRuntime().maxMemory()/1024/1024+"M");
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
