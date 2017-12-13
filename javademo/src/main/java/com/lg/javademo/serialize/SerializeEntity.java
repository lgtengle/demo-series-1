package com.lg.javademo.serialize;

import java.io.Serializable;
import java.util.Random;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/11/29 20:12
 *
 * @author leiguang
 */
public class SerializeEntity implements Serializable{

    private int i = 0;

    public void incre(){
        i--;
    }

    public int get(){
        System.out.println(new Random().nextInt(4));
        return i;
    }

    public void desc(){
        i--;
    }

}
