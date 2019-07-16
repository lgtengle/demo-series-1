package com.lg.algorithm.sort;

import com.lg.algorithm.common.CommonUtils;

/**
 * <p>
 * description: 冒泡排序，最坏时间复杂度为O(n2)，平均时间复杂度为O(n2)
 * </p>
 * Created on 2018/7/24 15:21
 *
 * @author leiguang
 */
public class BubbleSort {

    public static void main(String[] args){
        int[] arrays = {3,5,2,8,5,1,8,5,4,22,7,44,2,7,88};

        System.out.println("数组长度：" + arrays.length);
        for (int i = 0; i < arrays.length; i++) {
            //每轮比上一轮少比较一个
            for (int j = 0; j < arrays.length - i - 1; j++) {
                //升序排列
                if (arrays[j+1] < arrays[j]){
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                }
            }
        }

        CommonUtils.print(arrays);

    }
}
