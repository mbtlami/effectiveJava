package com.mbtlami.demo.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Description: effective-java
 * Created by mbtlami on 2018/12/28 下午 21:55
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field declaredField = Unsafe.class.getDeclaredFields()[0];
        declaredField.setAccessible(true);
        Unsafe unsafe = (Unsafe) declaredField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
