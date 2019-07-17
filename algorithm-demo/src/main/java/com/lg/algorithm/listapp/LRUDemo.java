package com.lg.algorithm.listapp;

import com.lg.algorithm.structure.entity.KVNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * description: lru算法，淘汰的策略是最近最少使用的元素
 * 经常用来做缓存
 * </p>
 * Created on 2019/7/17 9:10
 *
 * @author leiguang
 */
public class LRUDemo<K, V> {

    private KVNode head;

    private KVNode tail;

    //缓存的数据
    private Map<K, KVNode<K, V>> caches;

    /**
     * 缓存的容量
     */
    private int capacity = 16;

    public LRUDemo() {
        this.caches = new HashMap<>();
    }

    public LRUDemo(int capacity) {
        this();
        this.capacity = capacity;
    }

    public void put(K key, V value){
        KVNode<K, V> KVNode = new KVNode(value);
        caches.put(key, KVNode);

        if (head == null){
            head = tail = KVNode;
        }else {
            head.pre = KVNode;
            KVNode.next = head;
            head = KVNode;
        }

        //如果超过了容量，删除最后一个元素
        if (caches.size() > capacity){
            caches.remove(tail.key);
            tail.pre.next = null;
            tail = tail.pre;
        }
    }

    public V get(K key){
        KVNode<K, V> node = caches.get(key);
        if (node == null)
            return null;
        if (node != head){ //如果访问的不是头部节点
            if (node.next != null){ //如果访问的不是尾部节点
                node.next.pre = node.pre;
            } else {
                tail = tail.pre;
            }
            node.pre.next = node.next;
            head.pre = node;
            node.next = head;
            node.pre = null;

            head = node;
        }

        return node.val;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        KVNode tem = head;
        while (tem != null){
            sb.append(tem.val.toString() + " > ");
            tem = tem.next;
        }


        return "LRUDemo{" +
                "head=" + sb.toString() +
                '}';
    }

    public static void main(String[] args){
        LRUDemo<String, String> lruDemo = new LRUDemo<>(3);
        lruDemo.put("1", "1");
        lruDemo.put("2", "2");
        lruDemo.put("3", "3");

        lruDemo.get("1");
        lruDemo.put("4", "4");
        lruDemo.get("4");
        lruDemo.put("5", "5");

        System.out.println(lruDemo);
    }
}
