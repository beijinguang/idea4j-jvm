package com.idea4j.jvm.classload;

public class ConstClass {
    static {
        System.out.println("ConstClass init");
    }

    public static final String HELLOWORLD = "hello world";
}
