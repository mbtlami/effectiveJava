package com.mbtlami.demo.compare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @description: 实现了Comparable接口就表明这个实例具有内部排序。
 * 规则为：当对象小于、等于或大于指定对象的时候，分别返回负整数、零或正整数；如果有多个关键域，一般从最关键的域开始逐步比较到最不关键的域。
 * compareTo没有约定返回值的具体大小，只需要返回值的符号。
 * @author: tangwz
 * @date: 2018/9/2 22:22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User> {
    private Long userId;
    private String userName;

    @Override
    public int compareTo(User o) {
        int result1 = userId.compareTo(o.userId);
        if (result1 != 0) {
            return result1;
        }
        int result2 = userName.compareTo(o.userName);
        if (result2 != 0) {
            return result2;
        }
        return 0;
    }

    public static void main(String[] args) {
        User user1 = new User(12L,"abc");
        User user2 = new User(11L,"abc");
        User user3 = new User(13L,"abc");
        User user4 = new User(11L,"bbc");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        Collections.sort(users);

        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result1 = o1.userId.compareTo(o2.userId);
                if (result1 != 0) {
                    return result1;
                }
                int result2 = o1.userName.compareTo(o2.userName);
                if (result2 != 0) {
                    return result2;
                }
                return 0;
            }
        };

        users.sort(comparator.reversed());

        users.sort((o1, o2) -> {
            int result1 = o1.userId.compareTo(o2.userId);
            if (result1 != 0) {
                return result1;
            }
            int result2 = o1.userName.compareTo(o2.userName);
            if (result2 != 0) {
                return result2;
            }
            return 0;
        });

        System.out.println(users);
    }
}
