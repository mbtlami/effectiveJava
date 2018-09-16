package com.mbtlami.demo.serialized;

import java.io.Serializable;

/**
 * @description:
 * @author: tangwz
 * @date: 2018/9/16 7:26
 */

public abstract class Fruit implements Serializable{
    protected String name;

    public Fruit(String name) {
        this.name = name;
    }

    protected Fruit(){}

}
