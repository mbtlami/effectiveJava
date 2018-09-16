package com.mbtlami.demo.serialized;

import org.junit.Test;

import java.io.*;
import java.util.Date;

/**
 * @description:
 * @author: tangwz
 * @date: 2018/9/16 7:41
 */

public class Utils {
    public static void serialize(String filePath, Object o) {
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
            out.writeObject(o);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object deserialize(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            ObjectInput input = new ObjectInputStream(fileInputStream);
            Object object = input.readObject();
            input.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void test() {
        String filePath = "E:\\sourceCode\\effective-java\\src\\main\\resources\\object.txt";
        Apple apple = new Apple("红富士", "001");
        Utils.serialize(filePath, apple);
        Object deserialize = Utils.deserialize(filePath);
        System.out.println(deserialize);
    }

    @Test
    public void test1() {
        String filePath = "E:\\sourceCode\\effective-java\\src\\main\\resources\\object1.txt";
        Apple apple = new Apple("红富士", "001");
        Utils.serialize(filePath, apple);
    }

    @Test
    public void test2() {
        String filePath = "E:\\sourceCode\\effective-java\\src\\main\\resources\\object2.txt";
        Apple apple = new Apple("红富士", "001");
        Utils.serialize(filePath, apple);
    }

    @Test
    public void test21() {
        String filePath = "E:\\sourceCode\\effective-java\\src\\main\\resources\\object1.txt";
        Object deserialize = Utils.deserialize(filePath);
        System.out.println(deserialize);
    }

    @Test
    public void test22() {
        String filePath = "E:\\sourceCode\\effective-java\\src\\main\\resources\\object2.txt";
        Object deserialize = Utils.deserialize(filePath);
        System.out.println(deserialize);
    }

    @Test
    public void test3() {
        String filePath = "E:\\sourceCode\\effective-java\\src\\main\\resources\\object3.txt";
        Apple apple = new Apple("青苹果", new Date(), new Date());
        Utils.serialize(filePath, apple);
    }

    @Test
    public void test31() {
        String filePath = "E:\\sourceCode\\effective-java\\src\\main\\resources\\object3.txt";
        Object deserialize = Utils.deserialize(filePath);
        System.out.println(deserialize);
    }
}
