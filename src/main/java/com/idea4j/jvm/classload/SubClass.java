package com.idea4j.jvm.classload;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }
}
