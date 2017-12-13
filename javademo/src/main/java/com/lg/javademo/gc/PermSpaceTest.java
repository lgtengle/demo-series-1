package com.lg.javademo.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/12/1 12:18
 *
 * @author leiguang
 */
public class PermSpaceTest {
    
    public static void main(String[] args){
        /*int n = 1;
        String s = String.valueOf(n);
        System.out.println(s == s.intern());
        try {
            Thread.sleep(30*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        List<String> strs = new ArrayList<>();
        int i = 1725852;

        while(true) {
            strs.add(String.valueOf(i++).intern());
            System.out.println("We have created " + i + " constant String.");
        }
    }
}
