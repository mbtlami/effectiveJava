package com.mbtlami.demo.clone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * @description:
 * @author: tangwz
 * @date: 2018/9/1 18:47
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class People implements Cloneable, Serializable {
    private Long id;
    private String code;
    private String name;
    private Integer age;
    private Boolean isMarried;
    private Pet pet;
    private Node<String, Object>[] table;

    static class Node<K, V> implements Serializable {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Node<K, V> deepClone() {
            return new Node<K, V>(hash, key, value, next == null ? null : next.deepClone());
        }

        Node<K, V> deepCopy() {
            Node<K, V> node = new Node<K, V>(hash, key, value, next);
            for (Node p = node; p.next != null; p = p.next) {
                p.next = new Node(p.next.hash, p.next.key, p.next.value, p.next.next);
            }
            return node;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "hash=" + hash +
                    ", key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public People clone() {
        People people;
        try {
            people = (People) super.clone();
            if (pet != null) {
                people.pet = pet.clone();
            }
            if (table != null) {
                /*people.table = table.clone();*/
                people.table = new Node[table.length];
                for (int i = 0; i < people.table.length; i++) {
                    if (table[i] != null) {
                        /*people.table[i] = table[i].deepClone();*/
                        people.table[i] = table[i].deepCopy();
                    }
                }
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new AssertionError();//can't happen
        }
        return people;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isMarried=" + isMarried +
                ", pet=" + pet +
                '}';
    }

    public static Object cloneObject(Object obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(obj);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        return in.readObject();
    }


}
