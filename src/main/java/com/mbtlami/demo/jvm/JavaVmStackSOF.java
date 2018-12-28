package com.mbtlami.demo.jvm;

/**
 * Description: effective-java
 * Created by mbtlami on 2018/12/28 下午 21:07
 */
public class JavaVmStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVmStackSOF javaVmStackSOF = new JavaVmStackSOF();
        try {
            javaVmStackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println(javaVmStackSOF.stackLength);
            throw e;
        }
    }
}
