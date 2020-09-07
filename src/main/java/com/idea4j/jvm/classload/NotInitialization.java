package com.idea4j.jvm.classload;

/**
 * -XX:+TraceClassLoading
 * 非主动使用类字段
 * 对于静态字段，只有定义这个类的字段才会被初始化，子类引用父类的静态字段
 * 不会触发子类的初始化
 */
public class NotInitialization {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
