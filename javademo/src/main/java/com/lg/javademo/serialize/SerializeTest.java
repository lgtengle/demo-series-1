package com.lg.javademo.serialize;

import org.junit.Test;

import java.io.*;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/11/29 20:11
 *
 * @author leiguang
 */
public class SerializeTest {

    @Test
    public void serialize() throws IOException {
        SerializeEntity se = new SerializeEntity();
        se.incre();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SerializeTest.class.getClassLoader().getResource("ss.txt").getPath()));
        oos.writeObject(se);
        oos.flush();
        oos.close();
    }

    @Test
    public void diserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SerializeTest.class.getClassLoader().getResource("ss.txt").getPath()));
        Object o = ois.readObject();
        SerializeEntity se = (SerializeEntity) o;
        System.out.println(se.get());
    }
}
