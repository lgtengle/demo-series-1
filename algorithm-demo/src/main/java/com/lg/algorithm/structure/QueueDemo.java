package com.lg.algorithm.structure;

import com.lg.algorithm.structure.entity.Node;

/**
 * <p>
 * description: 单向队列，
 * </p>
 * Created on 2019/7/18 9:23
 *
 * @author leiguang
 */
public class QueueDemo {

    private Node head;

    private Node tail;

    public void add(Object obj){
        Node node = new Node(obj);

        if (head == null ){
            head = tail = node;
        }else {
            tail.next = node;
            tail = node;
        }
    }

    public Object poll(){
        if (head == null)
            return null;
        Node old = head;

        Node newHead = old.next;
        old.next = null;
        head = newHead;

        //释放尾部节点
        if (newHead == null){
            tail = null;
        }
        return old.val;
    }

    public static void main(String[] args){
        QueueDemo queueDemo = new QueueDemo();
        queueDemo.add(1);
        queueDemo.add(2);
        queueDemo.add(3);

        System.out.println(queueDemo.poll());
        System.out.println(queueDemo.poll());
        System.out.println(queueDemo.poll());
        System.out.println(queueDemo.poll());
    }
}
