package com.idea4j.jvm.classload.init;

public class Test {
    static class Parent {
        public static int a = 1;
        static {
            a = 2;
            c = 3;
        }
        public static int c = 2;
    }

    static class Sub extends Parent {
        public static  int b = a;
        public static  int d = c;
    }

    public static void main(String[] args) {
        System.out.println(Sub.b+" "+Sub.d);
    }

}
