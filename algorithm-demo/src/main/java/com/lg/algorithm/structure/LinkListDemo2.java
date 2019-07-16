package com.lg.algorithm.structure;

import com.lg.algorithm.structure.entity.Node;

/**
 * <p>
 * description: 单项链表实现方式1，使用两个节点来维持链表的关系
 * </p>
 * Created on 2019/7/15 11:19
 *
 * @author leiguang
 */
public class LinkListDemo2 {

    private Node head;

    private Node tail;

    public void add(Object val){
        Node newNode = new Node(val);
        if (null == head) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
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
                    //维护尾部节点
                    if (current == tail){
                        tail = pre;
                    }

                    //如果头部节点符合，直接删除头部节点
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
        LinkListDemo2 linkListDemo2 = new LinkListDemo2();
        linkListDemo2.add("1");
//        linkListDemo2.add("2");
//        linkListDemo2.add("3");
//        linkListDemo2.remove("3");
//        linkListDemo2.add("4");
//        linkListDemo2.remove("2");


        linkListDemo2.remove("1");
        System.out.println(linkListDemo2);
    }
}
