package com.lg.algorithm.structure;


import com.lg.algorithm.structure.entity.DNode;

/**
 * <p>
 * description: 双向链表实现 ： 需要维护首节点和尾节点，采用尾插入的方式
 * 删除采用从头部遍历的方式来查找元素
 *
 * </p>
 * Created on 2019/7/15 11:17
 *
 * @author leiguang
 */
public class DoubleQueueDemo {

    private DNode head;

    private DNode tail;


    public void add(Object val){
        DNode dNode = new DNode(val);
        if (head == null){
            head = tail = dNode;
        }else {
            tail.next = dNode;
            dNode.pre = tail;
            tail = dNode;
        }
    }

    public DNode remove(Object val){
        DNode current = head;
        while (current != null){
            if (current.val.equals(val)){
                if (current.pre == null && current.next == null){//只剩下一个节点
                    head = tail = null;
                } else if (current.pre == null){ //头节点匹配
                    head = current.next;
                    head.pre = null;
                } else if (current.next == null){ //尾节点匹配
                    tail = current.pre;
                    tail.next = null;
                } else { //其它
                    current.pre.next = current.next;
                    current.next.pre = current.pre;
                }
                return current;
            }
            current = current.next;
        }

        return null;
    }



    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        DNode tem = head;
        while (tem != null){
            sb.append(tem.val.toString() + " > ");
            tem = tem.next;
        }

        return "DoubleQueueDemo{" +
                "head=" + sb.toString() +
                '}';
    }

    public static void main(String[] args){
        DoubleQueueDemo doubleQueueDemo = new DoubleQueueDemo();
        doubleQueueDemo.add("1");
        doubleQueueDemo.add("2");
        doubleQueueDemo.add("3");

        doubleQueueDemo.remove("2");
        doubleQueueDemo.remove("3");
        doubleQueueDemo.remove("1");
        System.out.println(doubleQueueDemo);
    }
}
