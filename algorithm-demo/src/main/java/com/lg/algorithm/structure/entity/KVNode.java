package com.lg.algorithm.structure.entity;


/**
 * <p>
 * description:
 * </p>
 * Created on 2019/7/17 9:12
 *
 * @author leiguang
 */
public class KVNode<K, V> {

    public K key;

    public V val;

    public KVNode next;

    public KVNode pre;

    public KVNode(V val) {
        this.val = val;
    }
}
