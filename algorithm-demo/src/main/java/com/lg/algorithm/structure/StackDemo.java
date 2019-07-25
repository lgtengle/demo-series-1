package com.lg.algorithm.structure;

import com.lg.algorithm.structure.entity.DNode;

/**
 * <p>
 * description: 栈的实现，使用了双向链表
 * </p>
 * Created on 2019/7/22 9:26
 *
 * @author leiguang
 */
public class StackDemo {

    private DNode head;

    private DNode tail;

    public void add(Object obj){
        DNode node = new DNode(obj);

        if (head == null ){
            head = tail = node;
        }else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
    }

    public Object poll(){
        if (tail == null)
            return null;
        DNode old = tail;

        DNode newTail = old.pre;
        old.pre = null;

        //当尾节点和头节点相等时，释放头部节点
        if (newTail == null){
            head = null;
        } else {
            newTail.next = null;
        }
        tail = newTail;

        return old.val;
    }

    public static void main(String[] args){
        StackDemo stackDemo = new StackDemo();
        stackDemo.add(1);
        stackDemo.add(2);
        stackDemo.add(3);

        System.out.println(stackDemo.poll());
        System.out.println(stackDemo.poll());
        System.out.println(stackDemo.poll());
        stackDemo.add(3);
        stackDemo.add(2);
        stackDemo.add(1);
        System.out.println(stackDemo.poll());
        System.out.println(stackDemo.poll());
        System.out.println(stackDemo.poll());
        System.out.println(stackDemo.poll());

    }
}
