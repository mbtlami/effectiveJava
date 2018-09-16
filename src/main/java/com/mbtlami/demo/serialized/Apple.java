package com.mbtlami.demo.serialized;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: tangwz
 * @date: 2018/9/16 7:27
 */

public class Apple extends Fruit implements Serializable {
    private static final long serialVersionUID = -402113466254688744L;
    private String code;
    private transient int size;

    private Date startDay;
    private Date endDay;

    public Apple(String name, String code) {
        super(name);
        this.code = code;
    }

    public Apple(String name, Date startDay, Date endDay) {
        super(name);
        this.startDay = new Date(startDay.getTime());
        this.endDay = new Date(endDay.getTime());
        if (startDay.compareTo(endDay) > 0) {
            throw new IllegalArgumentException(startDay + "after" + endDay);
        }
    }

    private synchronized void writeObject(ObjectOutputStream s)
            throws IOException {
        s.defaultWriteObject();
        s.writeInt(size);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();// read in all fields
        size = s.readInt();
        startDay = new Date(startDay.getTime());
        endDay = new Date(endDay.getTime());
        if (startDay.compareTo(endDay) > 0) {
            throw new IllegalArgumentException(startDay + "after" + endDay);
        }
    }

    public Date getStartDay() {
        return new Date(startDay.getTime());
    }

    public Date getEndDay() {
        return new Date(endDay.getTime());
    }

    @Override
    public String toString() {
        return "Apple{" +
                "code='" + code + '\'' +
                "name='" + name + '\'' +
                ", size=" + size +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                '}';
    }
}
