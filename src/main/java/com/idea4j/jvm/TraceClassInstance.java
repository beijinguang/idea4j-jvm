package com.idea4j.jvm;


import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 类和对象跟踪
 * -XX：+TraceClassLoading 跟踪加载情况
 * -XX: +TraceClassUnloading 跟踪卸载情况
 */
public class TraceClassInstance {
    static MyClassLoader c1 = new MyClassLoader();
    static MethodInterceptor mi = new MyMethodInterceptor();

    public static void main(String[] args) throws CannotCompileException, InstantiationException, NotFoundException, IllegalAccessException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            JavaBeanObject v = createInstance2(i);
            c1 = new MyClassLoader();
        }
    }

    private static JavaBeanObject createInstance2(int i) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        CtClass c = ClassPool.getDefault().makeClass("Geym" + i);
        c.setSuperclass(ClassPool.getDefault().get("com.idea4j.jvm.JavaBeanObject"));
        Class clz = c.toClass(c1,null);
        return (JavaBeanObject) clz.newInstance();

    }
}
