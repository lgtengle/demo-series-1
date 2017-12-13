package com.lg.javademo.collections;


import org.junit.Test;

import java.util.*;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/14 13:00
 *
 * @author leiguang
 */
public class ListDemo {


    public List<String> getLists(){
        List<String> strings = Collections.synchronizedList(new ArrayList<String>());
        strings.add("11111");
        strings.add("22222");
        strings.add("33333");
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

    /**
     * 测试截取List子集合
     */
    @Test
    public void subListTest(){
        List<String> lists = getLists();
        List<String> strings = lists.subList(0, 2);
        lists = strings;
        System.out.println(lists);
        //System.out.println(lists.subList(1,2).size());
    }

    /**
     * 测试List转换成array
     */
    @Test
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

    /**
     * 测试移除List中的元素
     */
    @Test
    public void testRemove(){
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


    @Test
    public void testSort(){
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(6);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list);
    }

    public void main(){
        //Collection;
    }

}
