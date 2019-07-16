package com.lg.algorithm.structure;

import com.lg.algorithm.structure.entity.Node;

/**
 * <p>
 * description: 单项链表实现方式1，只使用一个节点来维持链表的关系
 * </p>
 * Created on 2019/7/15 11:19
 *
 * @author leiguang
 */
public class LinkListDemo1 {

    private Node head;

    public void add(Object val){
        Node newNode = new Node(val);
        if (null == head)
            head = newNode;
        else {
            newNode.next = head;
            head = newNode;
        }
    }

    public Node remove(Object val){
        if (val == null)
            return null;
        else {
            //上一个节点
            Node pre = null;
            //当前待判断的节点
            Node current = head;
            while (current != null){
                if (current.val.equals(val)){
                    if (pre == null)
                        head = current.next;
                    else {
                        pre.next = current.next;
                    }
                    return current;
                }
                pre = current;
                current = current.next;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node tem = head;
        while (tem != null){
            sb.append(tem.val.toString() + " > ");
            tem = tem.next;
        }
        return "LinkListDemo1{" +
                "list=" + sb.toString() +
                '}';
    }

    public static void main(String[] args){
        LinkListDemo1 linkListDemo1 = new LinkListDemo1();
        linkListDemo1.add("1");
        linkListDemo1.add("2");
        linkListDemo1.add("3");

        linkListDemo1.remove("3");
        linkListDemo1.remove("2");
        linkListDemo1.remove("1");
        System.out.println(linkListDemo1);
    }
}
