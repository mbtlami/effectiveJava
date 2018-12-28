package com.mbtlami.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: effective-java
 * Created by mbtlami on 2018/12/28 下午 20:39
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
