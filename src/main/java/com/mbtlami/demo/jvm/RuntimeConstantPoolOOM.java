package com.mbtlami.demo.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: effective-java
 * Created by mbtlami on 2018/12/28 下午 21:23
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
       /* List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i).intern());
        }*/
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invoke(o, objects));
        }
    }

    static class OOMObject {
    }
}
