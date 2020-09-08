package com.idea4j.jvm.classload.classloader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Object o = classLoader.loadClass("com.idea4j.jvm.classload.classloader.ClassLoaderTest").newInstance();
        System.out.println(o.getClass());
        System.out.println(o instanceof com.idea4j.jvm.classload.classloader.ClassLoaderTest);
        System.out.println(o.getClass().equals(ClassLoaderTest.class) );
    }
}
