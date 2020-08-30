package com.idea4j.jvm.oom;

/**
 * 局部变量表中的字空间是可以重用的
 * jclasslib
 * test1 最大局部变量表容量2+1=3 ,long 2字，this 1 字
 * test2 最大局部变量表容量2+2+1=3，
 */
public class WordReuse {
    public void test1() {
        {
            long a = 0;
        }
        long b = 0;
    }

    public void test2() {
        long a = 0;
        long b = 0;
    }
}
