package com.mbtlami.demo.clone;

import java.io.IOException;

/**
 * @description:
 * @author: tangwz
 * @date: 2018/9/1 18:47
 */

public class Test {
    public static void main(String[] args) {
        Pet dog = new Pet("dog");
        People.Node<String, Object>[] nodes = new People.Node[10];
        People.Node<String, String> node12 = new People.Node<>("mbtlami12".hashCode(), "mbtlami12", "mbtlami12", null);
        People.Node<String, String> node11 = new People.Node<>("mbtlami11".hashCode(), "mbtlami11", "mbtlami11", node12);
        nodes[0] = new People.Node("mbtlami1".hashCode(), "mbtlami1", "mbtlami1", node11);
        People.PeopleBuilder builder = People.builder();
        People people = builder.id(12L)
                .name("mbtlami")
                .code("2018")
                .pet(dog)
                .age(25)
                .table(nodes)
                .build();
        System.out.println(people);
        People clone = people.clone();
        System.out.println(clone);

        people.getPet().setName("cat");
        System.out.println(clone);


        People.Node<String, Object> stringObjectNode = clone.getTable()[0];
        System.out.println(stringObjectNode);
        People.Node<String, Object>[] table = people.getTable();
        table[0].next = null;
        System.out.println(stringObjectNode);

        try {
            Object object = People.cloneObject(people);
            System.out.println(object);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
