package com.idea4j.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * jvm:-XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
 */
public class Metaspace {
    public static void main(String[] args) {
        long count = 0L;
        while (true) {
            Enhancer enhancer = new Enhancer();
            //通过CGLIB的Enhancer生成了一个Car的代理子类
            enhancer.setSuperclass(Car.class);
//            enhancer.setUseCache(false);
            enhancer.setUseCache(true);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if (method.getName().equals("run")) {
                        System.out.println("Before run, security checking...");
                        return methodProxy.invokeSuper(o, objects);
                    } else {
                        return methodProxy.invokeSuper(o, objects);
                    }
                }
            });

            Car car = (Car) enhancer.create();
            car.run();

            System.out.println("Created " + ++count +" Car.");
        }
    }

    static class Car {
        public void run() {
            System.out.println("Car is running...");
        }
    }

    static class SafeCar extends Car{
        @Override
        public void run() {
            System.out.println("Car is running...");
            super.run();
        }
    }
}
