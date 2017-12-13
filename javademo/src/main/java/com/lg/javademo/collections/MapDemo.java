package com.lg.javademo.collections;


import org.junit.Test;

import java.util.*;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/25 14:56
 *
 * @author leiguang
 */
public class MapDemo {


    @Test
    public void testMapSource(){


    }

    @Test
    public void testIterator(){
        Map<String, String> map = new HashMap<>();
        map.put("test1", "1111");
        map.put("test2", "2222");
        map.put("test3", "3333");
        map.put("test4", "4444");
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        System.out.println(strings.size());
        System.out.println(map.size());
        /*Collection<String> values = map.values();
        for (String value : values) {
            values.remove(value);
        }*/
    }

    @Test
    public void testTreeMap(){
        //TreeMap;
    }
}
