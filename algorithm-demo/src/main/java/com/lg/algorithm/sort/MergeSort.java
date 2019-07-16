package com.lg.algorithm.sort;

import com.lg.algorithm.common.CommonUtils;

/**
 * <p>
 * description:归并排序，时间复杂度O(nlgn)
 * </p>
 * Created on 2018/7/24 17:12
 *
 * @author leiguang
 */
public class MergeSort {

    public static void main(String[] args){
        int[] arrays = CommonUtils.randomArray(85);

        CommonUtils.print(arrays);

        int[] tempArrays = new int[arrays.length];
        divide(arrays, tempArrays, 0, arrays.length - 1);
        CommonUtils.print(arrays);
    }

    public static void divide(int[] arrays, int[] tempArrays, int left, int right){
        if (left < right){
            int middle = (left + right) / 2;

            divide(arrays, tempArrays, left, middle);

            divide(arrays, tempArrays, middle + 1, right);

            merge(arrays, tempArrays, left, middle, right);
        }
    }

    public static void merge(int[] arrays, int[] tempArrays, int left, int middle, int right){
        int tempMiddle = middle;
        int tempLeft = left;

        // 列表合并后的长度
        int tempLength = right - left + 1;

        middle++;
        int tempIndex = left;

        while (left <= tempMiddle && middle <= right){
            if (arrays[left] >= arrays[middle]){
                tempArrays[tempIndex++] = arrays[left++];
            }else
                tempArrays[tempIndex++] = arrays[middle++];
        }
        //
        while (left <= tempMiddle){
            tempArrays[tempIndex++] = arrays[left++];
        }

        while (middle <= right){
            tempArrays[tempIndex++] = arrays[middle++];
        }

        // 交换数据
        for (int i = 0; i < tempLength; i++) {
            arrays[tempLeft] = tempArrays[tempLeft];
            tempLeft++;
        }
    }
}
