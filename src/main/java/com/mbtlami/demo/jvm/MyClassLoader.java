package com.mbtlami.demo.jvm;

import java.io.*;

/**
 * Description: effective-java
 * Created by mbtlami on 2019/01/02 23:35
 */
public class MyClassLoader extends ClassLoader {
    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    public MyClassLoader() {
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] bytes = getData(className);
        if (bytes == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(className, bytes, 0, bytes.length);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int num;
            while ((num = fileInputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, num);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("E:\\sourceCode\\effective-java\\target\\classes");
        try {
            Class<?> aClass1 = myClassLoader.findClass("com.mbtlami.demo.util.TestString");
            System.out.println(aClass1.newInstance());
//            Class<?> aClass2 = myClassLoader.findClass("com.mbtlami.demo.util.TestString");// LinkageError
            ClassLoader classLoader = aClass1.getClassLoader();
            while (classLoader != null) {
                System.out.println(classLoader.getClass().getName());
                classLoader = classLoader.getParent();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
