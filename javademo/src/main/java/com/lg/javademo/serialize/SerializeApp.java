package com.lg.javademo.serialize;

import java.io.*;

/**
 * <p>
 * description:
 *
 * 1.问题一：当序列化后通过网络传输时，接收方反序列化后通过接口来接收是否可以？
 * </p>
 * Created on 2017/9/13 19:41
 *
 * @author leiguang
 */
public class SerializeApp {
    /*public static void main(String[] args) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ObjectOutputStream oout = new ObjectOutputStream(baos);
        TestSerialize ts = new TestSerialize();
        oout.writeObject(ts);
        byte[] bytes = baos.toByteArray();
        oout.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream oin = new ObjectInputStream(bais);
        Object newPerson = oin.readObject(); // 没有强制转换到Person类型
        oin.close();
        System.out.println(newPerson);
    }

    public static void InterfaceTestSerialize(){

    }

    static class TestSerialize implements Baseable, Serializable{

        private InValue1 inValue = new InValue1();

        public TestSerialize(){
            System.out.println(">>>>>>>>>>TestSerialize");
        }

        public InValue1 getInValue() {
            return inValue;
        }
        public void setInValue(InValue1 inValue) {
            this.inValue = inValue;
        }
    }


    static class InValue1 implements Serializable{
        //private InValue2 inValue2 = new InValue2();
    }

    static class InValue2 {

    }*/
}
