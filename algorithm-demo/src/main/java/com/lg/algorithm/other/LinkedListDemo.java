package com.lg.algorithm.other;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/7/24 18:38
 *
 * @author leiguang
 */
public class LinkedListDemo {



    public static void reverseLinkedList(){
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, node1);
        Node node3 = new Node(3, node2);
        Node node4 = new Node(4, node3);

        Node pre = node4;
        Node current = node4.next;
        node4.next = null;
        Node next = null;

        while (current != null){
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }

    }
}
