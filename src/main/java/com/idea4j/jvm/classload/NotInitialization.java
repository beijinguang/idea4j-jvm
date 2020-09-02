package com.idea4j.jvm.classload;

/**
 * -XX:+TraceClassLoading
 */
public class NotInitialization {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
