package com.lg.javademo.other;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/10/18 19:53
 *
 * @author leiguang
 */
public class IntegerTest {
    public static void main(String[] args){
        Integer n = new Integer(45);
        System.out.println(n.hashCode());
        n++;
        System.out.println(n.hashCode());
        //System.out.println(n);
    }
}
