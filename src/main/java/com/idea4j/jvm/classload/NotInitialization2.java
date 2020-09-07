package com.idea4j.jvm.classload;

/**
 * -XX:+TraceClassLoading
 */
public class NotInitialization2 {
    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10];
    }
}
