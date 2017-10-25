/*
 *  广州丰石科技公司有限公司拥有本软件版权2016并保留所有权利。
 *  Copyright 2016, GuangZhou Rich Stone Data Technologies Company Limited,
 *  All rights reserved.
 */

package com.lg;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <b><code>CommonUtils</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2016/12/13 17:25
 *
 * @author tangguzhao
 * @version 0.1.0
 * @since mintaka 0.1.0
 */
public class CommonUtils {


    /**
     * get prefix by lac ci
     *
     * @param lacCi lac ci
     * @return string
     */
    public static String getHbasePrefix(String lacCi) {
        int prefix = Math.abs((lacCi.hashCode() * 127) % 150);
        return String.valueOf(prefix);
    }


    /**
     * get InterMark Count By Param
     *
     * @param count count
     * @return string
     */
    public static String getInterMarkCountByParam(int count) {
        if (count == 0) {
            return "?";
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < count; i++) {
                sb.append("?,");
            }
            String result = sb.substring(0, sb.length() - 1);
            return result;
        }
    }

    /**
     * string array conversion Integer array
     *
     * @param array array
     * @return integer array
     */
    public static Integer[] conversionStringToInteger(String[] array) {
        if (array == null || array.length == 0) {
            return new Integer[0];
        }
        int arrayLen = array.length;
        Integer[] result = new Integer[arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            result[i] = Integer.parseInt(array[i]);
        }
        return result;
    }

    /**
     * string array conversion Integer array
     *
     * @param list list
     * @return integer array
     */
    public static Integer[] conversionStringToInteger(List<String> list) {
        if (ObjectUtils.listIsEmpty(list)) {
            return new Integer[0];
        }
        int arrayLen = list.size();
        Integer[] result = new Integer[arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            result[i] = Integer.parseInt(list.get(i));
        }
        return result;
    }


    /**
     * src remove all oth
     *
     * @param src
     * @param oth
     * @return
     */
    public static Set removeAll(Set src, Set oth) {
        if (ObjectUtils.settIsEmpty(src)) {
            return new HashSet();
        }
        if (ObjectUtils.settIsEmpty(oth)) {
            return src;
        }
        LinkedList result = new LinkedList(src);//大集合用linkedlist
        HashSet othHash = new HashSet(oth);//小集合用hashset
        Iterator iter = result.iterator();//采用Iterator迭代器进行数据的操作
        while (iter.hasNext()) {
            if (othHash.contains(iter.next())) {
                iter.remove();
            }
        }
        return new HashSet(result);
    }

    /**
     * 很多lac ci 分组
     *
     * @param collection
     * @return
     */
    public static List<Set<String>> dataSplit(Collection<String> collection, int threadCount) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        List<String> list = new ArrayList<>(collection);
        int number = list.size() / threadCount;
        List<Set<String>> setList = new ArrayList<>();
        if (number < 8) {
            Set<String> set = new HashSet<>(list);
            setList.add(set);
            return setList;
        }
        for (int i = 0; i < threadCount; i++) {
            int start = i * number;
            int end = (i + 1) * number;
            if (i == threadCount - 1) {
                end = list.size();
            }
            Set<String> set = new HashSet<>(list.subList(start, end));
            setList.add(set);
        }
        return setList;
    }

    /**
     * 数据类型转换
     *
     * @param source
     * @param result
     */
    public static void dataTypeConversion(Set<String> source, Set<Long> result) {
        if (ObjectUtils.settIsEmpty(result)) {
            result = new HashSet<Long>();
        }
        if (ObjectUtils.settIsEmpty(source)) {
            return;
        }
        for (String str : source) {
            result.add(Long.parseLong(str));
        }
    }

    /**
     * 数据类型转换
     *
     * @param source
     */
    public static Set<Long> dataTypeConversion(Set<String> source) {
        Set<Long> result = new HashSet<Long>();
        ;
        if (ObjectUtils.settIsEmpty(source)) {
            return result;
        }
        for (String str : source) {
            result.add(Long.parseLong(str));
        }
        return result;
    }

    /**
     * @param lengthArr
     * @return
     */
    public static int getLengthByJavaClassTypeLength(int... lengthArr) {
        int countLen = 0;
        for (int num : lengthArr) {
            countLen += num;
        }
        return countLen;
    }


    /**
     * 把partMap数据合并放到resultMap中。
     *
     * @param resultMap
     * @param partMap
     */
    public static <T, F, K extends Collection<F>> void merge2ResultMap(Map<T, K> resultMap, Map<T, K> partMap) {
        for (Map.Entry<T, K> entry : partMap.entrySet()) {
            T key = entry.getKey();
            if (resultMap.containsKey(key)) {
                resultMap.get(key).addAll(entry.getValue());
            } else {
                resultMap.putAll(partMap);
            }
        }
    }

    public static String convertCollectionToStr(Collection collection, String interval) {
        Iterator iterable = collection.iterator();
        StringBuilder sb = new StringBuilder();
        if (iterable.hasNext()) {
            sb.append(iterable.toString());
        }
        while (iterable.hasNext()) {
            sb.append(',');
            sb.append(iterable.toString());
        }
        return sb.toString();
    }


    /**
     * 获取later分钟之前的时间
     *
     * @param later
     * @return
     */
    public static long getStartTime(int later) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -later);
        return calendar.getTime().getTime();

    }

    public static long getEndTime() {
        return new Date().getTime();
    }

    public static String converTimeStampToStr(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static long[] converStrToLongArray(String[] strings) {
        long[] result = new long[strings.length];

        for (int i = 0; i < strings.length; i++) {
            result[i] = Long.parseLong(strings[i]);
        }
        return result;
    }


    public static Set<String> converStrToSet(String str){
        String[] split = str.split(",");
        List<String> strings = Arrays.asList(split);
        Set<String> result = new HashSet<>();
        result.addAll(strings);
        return result;
    }


    public static Object converByteToObject(byte[] bytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream bis = new ObjectInputStream(bais);
            try {
                return bis.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
