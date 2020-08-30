package com.idea4j.jvm;

import java.util.Vector;

/**
 *
 */
public class JVMParameters {
    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
    }

    /**
     * -Xmx5M
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
}
