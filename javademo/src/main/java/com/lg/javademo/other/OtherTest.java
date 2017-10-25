package com.lg.javademo.other;


import java.util.Arrays;


/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/14 20:12
 *
 * @author leiguang
 */
public class OtherTest {


    public void testFinal(){
        String s = "ddd|ddd|444";
        System.out.println(Arrays.toString(s.split("\\|")));
    }


    public void testBin(){
        //min 10000000000000000000000000000000 ‭-2147483648‬
        //max  1111111111111111111111111111111  2147483647


        //-1 11111111111111111111111111111111
        //-2 11111111111111111111111111111110
        // 源 0000 0000 0000 0001
        // 移位后 0000 0000 0011 1100
        // 1<< 31  -2147483648  1000 0000 0000 0000 0000 0000 0000 0000
        //-1<< 31  -2147483648  1000 0000 0000 0000 0000 0000 0000 0000
        //1 << 30  1073741824   1000000000000000000000000000000
        //-1 << 30 -1073741824  1100 0000 0000 0000 0000 0000 0000 0000
        //2 << 31
        /*System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(-1 >> 1);*/


    }

}
