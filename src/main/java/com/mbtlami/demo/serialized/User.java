package com.mbtlami.demo.serialized;

import java.io.*;

/**
 * @description: 序列化代理
 * @author: tangwz
 * @date: 2018/9/16 6:51
 */

public class User implements Serializable {
    /**
     * 序列版本UID，避免序列版本不一致导致反序列化失败
     * 如果没有显示指定该标识号，系统会自动调用一个复杂的运算再运行时产生这个标识号。
     * 这个生成的值受类的名称、它他所实现的接口的名称、以及所有的共有和私有的成员的名称所影响
     */
    private static final long serialVersionUID = 3418138480139736038L;

    private String userCode;

    public User(String userCode) {
        this.userCode = userCode;
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = -2124902706946136200L;
        private final String userCode;

        SerializationProxy(User user) {
            this.userCode = user.userCode;
        }

        private Object readResolve() {
            return new User(userCode);
        }
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        throw new InvalidClassException("Proxy required");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User("001");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("serial.user"));
        out.writeObject(user);
        out.flush();
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("serial.user"));
        User deserUser = (User) in.readObject();
        System.out.println("main: " + user);
        in.close();

        System.out.println(user == deserUser);// false
    }
}
/**
 * 将一个对象编码成一个字节流称作将该对象序列化
 * InvalidClassException
 * 序列化需要注意的几点：
 * 如果使用默认的序列化形式，之后修改这个类的内部表示法，可能会导致序列化形式的不兼容（利用类的旧版本序列化，使用新版本反序列化将会导致失败）。
 * java.io.InvalidClassException: com.mbtlami.demo.serialized.Apple; local class incompatible: stream classdesc serialVersionUID = -6848891193333517364, local class serialVersionUID = -1046126841329705599
 * 设计一个高品质的自定义的序列化形式是比较重要的，要能合理的描述对象的状态；
 * 序列化具备和构造器相同的特点，它是一种语言之外的对象创建机制。使用构造器创建对象的约束，反序列化也要保证这些约束。
 * 可序列化的类被修改后，需要检查“在新版本中序列化一个实例，在旧版本中发序列化”，反之亦然，确保“序列化-反序列化成功”。
 * 为继承而设计的类应该尽可能少的去实现Serializable接口。如果超类不是可序列化的，就不可能编写出可序列化的子类，且超类没有提供无参构造器，子类序列化会报InvalidClassException: no valid constructor异常
 * 普通的内部类不要实现Serializable，编译器会为内部类产生一个合成域来保存指向外围实例的引用，这个域没有明确规定如何对应到类定义中，因此内部类的默认序列化形式是不确定的。
 * 通过同步实现的线程安全的对象，序列化方法writeObject也需要加上同步
 * 反序列化后需要检查对象的有效性
 * 避免伪造字节流攻击，对实例中的域进行保护性拷贝
 * 一个类实现了序列化就不再是单例类
 * 一个类定义了一个readResolve方法，在反序列化之后，新建对象上的readResolve就会被调用，然后，该方法返回的对象引用将被返回取代新建对象。
 * 依赖readResolve进行实例控制，则带有对象引用类型的所有实例域都必须申明为transient
 */