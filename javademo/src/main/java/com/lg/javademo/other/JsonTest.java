package com.lg.javademo.other;


import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/7/27 12:22
 *
 * @author leiguang
 */
public class JsonTest {
    public static void main(String[] args){
        /*HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("test", 1);
        stringObjectHashMap.put("other", "efdsf");
        JSONObject jsonObject = JSONObject.fromObject(stringObjectHashMap);
        System.out.println(jsonObject.toString());*/

        List<String> s = new ArrayList<String>();
        s.add("33");
        s.add("44");
        System.out.println(s.subList(0, s.size()));
    }
}
