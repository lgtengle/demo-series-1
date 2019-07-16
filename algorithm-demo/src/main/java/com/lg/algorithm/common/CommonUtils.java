package com.lg.algorithm.common;

import java.util.Random;

/**
 * <p>
 * description:
 * </p>
 * Created on 2018/7/24 15:32
 *
 * @author leiguang
 */
public class CommonUtils {

    public static void print(int[] arrays){
        StringBuilder sb = new StringBuilder();
        sb.append(arrays[0]);
        for (int i = 1; i < arrays.length; i++) {
            sb.append("," + arrays[i]);
        }
        System.out.println("数组长度：" + arrays.length + ";  数组内容：" + sb.toString());
    }

    public static int[] randomArray(){
        return randomArray(20);
    }
    public static int[] randomArray(int length){
        int[] arrays = new int[length];
        for (int i = 0; i < length; i++) {
            arrays[i] = new Random().nextInt(100);
        }
        return arrays;
    }

    public static void swap(int[] list, int left, int right) {
        int temp;
        if (list != null && list.length > 0) {
            temp = list[left];
            list[left] = list[right];
            list[right] = temp;
        }
    }
}
