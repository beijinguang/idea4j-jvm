package com.idea4j.jvm.classload;

/**
 *
 */
public class B extends A {
    static {
        System.out.println("a");
    }

    public B() {
        System.out.println("b");
    }

    public static void main(String[] args) {
        A ab = new B();
        ab = new B();
    }
}