package com.lg.javademo.collections;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/14 13:00
 *
 * @author leiguang
 */
public class ListDemo {

    public static void main(String[] args){
        testRemove();
    }


    public static List<String> getLists(){
        List<String> strings = Collections.synchronizedList(new ArrayList<String>());
        strings.add("1");
        strings.add("2");
        strings.add("3");
        return strings;
    }


    static class ContentDemo {
        public int n = 0;
        public ContentDemo(){
            System.out.println("none args constructor");
        }
        public ContentDemo(int n) {
            System.out.println("args constructor");
            this.n = n;
        }
    }

    public static void subListTest(){
        List<String> lists = getLists();
        List<String> strings = lists.subList(0, 1);
        System.out.println(strings.size());
        System.out.println(lists.subList(1,2).size());
    }

    public void testToArray(){
        List<ContentDemo> cd = new ArrayList<>();
        cd.add(new ContentDemo(1));
        cd.add(new ContentDemo(3));
        cd.add(new ContentDemo(6));
        Object[] objects = cd.toArray();
        ContentDemo object = (ContentDemo) objects[0];
        object.n++;
        System.out.println(object.n + "---" + cd.get(0).n);


        ContentDemo[] rr = new ContentDemo[5];
        cd.toArray(rr);
        System.out.println(rr.length);
    }

    public static void testRemove(){
        List<ContentDemo> cd = new ArrayList<>();
        cd.add(new ContentDemo(1));
        cd.add(new ContentDemo(3));
        cd.add(new ContentDemo(6));

        cd.subList(2, 3).clear();
        System.out.println(cd.size());
        /*cd.add(null);
        cd.add(null);

        while (cd.contains(null))
            cd.remove(null);
        System.out.println(cd.size());*/
    }


}
