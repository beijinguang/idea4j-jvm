package com.idea4j.jvm.oom;


public class Stack {
    public static long counter = 0L;
    /**
     * -XX:ThreadStackSize=1m
     * @param args
     */
    public static void main(String[] args) {
        work();
    }
    private static void work() {
        System.out.println("第" + ++counter + "次调用work方法");
        work();
    }
}
