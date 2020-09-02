package com.idea4j.jvm.classload;

/**
 * -XX:+TraceClassLoading
 */
public class NotInitialization3 {
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWORLD);
    }

}
