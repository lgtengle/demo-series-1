package com.lg.javademo.test1;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/7/19 11:52
 *
 * @author leiguang
 */
public class HeapOOM {

    static class OOMObject{
        
    }
    
    public static void main(String[] args){
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }


}
