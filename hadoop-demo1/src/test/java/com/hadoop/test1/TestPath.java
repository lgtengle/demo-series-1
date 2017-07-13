package com.hadoop.test1;

import org.apache.hadoop.fs.Path;

import java.text.SimpleDateFormat;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/7/4 15:32
 *
 * @author leiguang
 */
public class TestPath {
    
    public static void main(String[] args) throws Exception{
        /*Path path = new Path("input/");
        System.out.println(path.depth());*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i=0;i<12;i++){
            String s = "";
            int j = i*5;
            if (j<10)
                s = "0";
            s = "2017-07-12 16:" + s + j + ":00";

            System.out.println(s + "             "+sdf.parse(s).getTime());
        }
    }
}
