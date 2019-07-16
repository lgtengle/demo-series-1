package com.lg.algorithm.sort;

import com.lg.algorithm.common.CommonUtils;

/**
 * <p>
 * description: 选择排序,最坏时间复杂度为O(n2)，平均时间复杂度为O(n2)
 * </p>
 * Created on 2018/7/24 16:41
 *
 * @author leiguang
 */
public class SelectionSort {

    public static void main(String[] args){
        int[] arrays = CommonUtils.randomArray();

        CommonUtils.print(arrays);
        for (int i = 0; i < arrays.length; i++) {
            int a = arrays[i];
            int index = i;
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[j] > a){
                    a = arrays[j];
                    index = j;
                }
            }
            if (index != i)
                CommonUtils.swap(arrays, i, index);
        }

        CommonUtils.print(arrays);
    }
}
