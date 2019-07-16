package com.lg.algorithm.sort;

import com.lg.algorithm.common.CommonUtils;

/**
 * <p>
 * description: 快速排序，最坏时间复杂度为O(n2)，平均时间复杂度为O(n*log2n)
 * </p>
 * Created on 2018/7/24 15:39
 *
 * @author leiguang
 */
public class QuickSort {

    public static void main(String[] args){
        int[] arrays = CommonUtils.randomArray(95);
        CommonUtils.print(arrays);


        quictSort(arrays, 0, arrays.length -1);
        CommonUtils.print(arrays);
    }

    public static void quictSort(int[] arrays, int head, int tail){
        if (head < tail){
            int middle = findMiddle(arrays, head, tail);

            quictSort(arrays, head, middle - 1);

            quictSort(arrays, middle + 1, tail);
        }
    }

    /**
     * 找出第一个元素的中间位置
     * @param arrays
     * @param head
     * @param tail
     * @return
     */
    public static int findMiddle(int[] arrays, int head, int tail){
        int length = tail - head;
        int[] temp = new int[length + 1];
        int first = arrays[head];

        int leftCount = -1, rightCount = -1;

        for (int i = head + 1; i <= tail; i++) {
            int a = arrays[i];
            if (a <= first){
                temp[++leftCount] = a;
            }else {
                temp[length - ++rightCount] = a;
            }
        }

        int middle = leftCount + 1;
        temp[middle] = first;

        System.arraycopy(temp, 0, arrays, head, length + 1);
        return head + middle;
    }

    /*****************************第三方实现*****************************/

    /**
     * 分割数组，找到分割点
     */
    public static int partition(int[] list, int left, int right) {
        // 用数组的第一个元素作为基准数
        int first = list[left];
        while (left < right) {
            while (left < right && list[right] >= first) {
                right--;
            }
            // 交换
            swap(list, left, right);

            while (left < right && list[left] <= first) {
                left++;
            }
            // 交换
            swap(list, left, right);
        }
        // 返回分割点所在的位置
        return left;
    }

    /**
     * 交换数组中两个位置的元素
     */
    public static void swap(int[] list, int left, int right) {
        int temp;
        if (list != null && list.length > 0) {
            temp = list[left];
            list[left] = list[right];
            list[right] = temp;
        }
    }
}
