package com.mbtlami.demo.clone;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: tangwz
 * @date: 2018/9/1 19:53
 */

@Data
public class Pet implements Cloneable, Serializable {
    private String name;

    public Pet(String name) {
        this.name = name;
    }

    @Override
    public Pet clone() {
        try {
            return (Pet) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new AssertionError();
        }
    }
}
