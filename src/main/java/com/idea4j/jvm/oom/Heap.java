package com.idea4j.jvm.oom;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    /**
     * -Xms5m -Xmx5m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xloggc:gc.log
     * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
     * @param args
     */
    public static void main(String[] args) {
        long counter = 0L;
        List<Object> list = new ArrayList<Object>();

        while (true) {
            list.add(new Object());
            System.out.println("当前创建了第" + ++counter + "个对象");
        }
    }
}
