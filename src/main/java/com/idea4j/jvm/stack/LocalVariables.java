package com.idea4j.jvm.stack;

/**
 * java 虚拟机栈
 */
public class LocalVariables {
    public static void main(String[] args) {
        test1();
        System.gc();
        System.out.println("second explict gc over");
    }

    private static void test5() {
        {
            int c = 0;
            byte[] b = new byte[6 * 1024 * 1024];
        }
        int a = 0;
        int d = 0;
        System.gc();
        System.out.println("first explict gc over");
    }

    private static void test4() {
        {
            int c = 0;
            byte[] b = new byte[6 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
        System.out.println("first explict gc over");
    }

    /**
     * a 复用了b的字，b的空间可以被回收
     */
    private static void test3() {
        {
            byte[] b = new byte[6 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
        System.out.println("first explict gc over");
    }

    /**
     * GC可以回收，赋值为null将销毁局部变量表中的数据
     */
    private static void test2() {
        {
            byte[] b = new byte[6 * 1024 * 1024];
            b = null;
        }
        System.gc();
        System.out.println("first explict gc over");
    }

    /**
     *变量a的作用域只限于最近的大括号中，gc调用时，变量b超出他的作用范围
     */
    private static void test1() {
        {
            byte[] b = new byte[6 * 1024 * 1024];
        }
        System.gc();
        System.out.println("first explict gc over");
    }

}
