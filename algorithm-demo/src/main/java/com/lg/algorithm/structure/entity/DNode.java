package com.lg.algorithm.structure.entity;


/**
 * <p>
 * description:
 * </p>
 * Created on 2019/7/17 9:12
 *
 * @author leiguang
 */
public class DNode<V> {


    public V val;

    public DNode next;

    public DNode pre;

    public DNode(V val) {
        this.val = val;
    }
}
