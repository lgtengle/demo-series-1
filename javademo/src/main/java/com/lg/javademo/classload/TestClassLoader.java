package com.lg.javademo.classload;

import sun.misc.Launcher;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/10/10 16:54
 *
 * @author leiguang
 */
public class TestClassLoader {

    public static Map map = new HashMap();

    static {
        System.out.println("i am loading...");
    }

    private static String BASE_PATH = "D:\\project\\work1\\demo-series-1\\javademo\\target\\classes\\com\\lg\\javademo\\classload\\";

    public static void main(String[] args) throws Exception {

        System.out.println(System.getProperty("java.class.path"));

        TestClassLoader model2 = new TestClassLoader();
        TestClassLoader.map.put("1", "1");
        System.out.println("默认加载器-----");
        System.out.println("map hashCode:" + TestClassLoader.map.hashCode());
        URL resource = model2.getClass().getClassLoader().getResource("com/lg/javademo/classload/TestClassLoader.class");
        System.out.println(resource.toString());


        System.out.println();
        System.out.println("自定义加载器-----");
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name)
                    throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = this.getResourceAsStream(fileName);
                    if (is == null)
                        return super.loadClass(name);
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    return defineClass(name, data, 0, data.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Class<?> myClass = myClassLoader.loadClass("com.lg.javademo.classload.TestClassLoader");
        java.lang.reflect.Method get = myClass.getMethod("get");
        Object invoke = get.invoke(myClass.newInstance());
        System.out.println("map hashCode:" + invoke.hashCode());

        java.lang.reflect.Method size = myClass.getMethod("size");
        size.invoke(myClass.newInstance());

    }

    public Map get(){
        return map;
    }

    public void size(){
        System.out.println( "size:" + map.size());
    }
}
