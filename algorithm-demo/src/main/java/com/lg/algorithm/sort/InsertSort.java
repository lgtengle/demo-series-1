package com.lg.algorithm.sort;

import com.lg.algorithm.common.CommonUtils;

/**
 * <p>
 * description:插入排序
 * </p>
 * Created on 2018/7/24 16:55
 *
 * @author leiguang
 */
public class InsertSort {

    public static void main(String[] args){
        int[] arrays = CommonUtils.randomArray();

        CommonUtils.print(arrays);
        for (int i = 1; i < arrays.length; i++) {
            int temp = arrays[i];

            int j = i - 1;
            for (; j >= 0 && arrays[j] > temp; j--) {
                arrays[j + 1] = arrays[j];
            }

            arrays[j + 1] = temp;
        }
        CommonUtils.print(arrays);
    }
}
