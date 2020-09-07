package com.idea4j.jvm.classload;

/**
 * static语句块只能访问到语句块之前的变量
 */
public class InitialzingClass {

    static int a = 0;
    static {
        a = 1;
        b = 1;
    }

    static int b = 0;

    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(b);

    }
}
