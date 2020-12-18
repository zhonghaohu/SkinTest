package com.example.myapplication;

import org.junit.Test;

import java.util.Arrays;

//堆排序
public class Sort {

    @Test
    public void test() {
        int[] array = gennerateArray(1000000, 1000000);
//        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        heapSort(array);
        System.out.println("排序时间为：" + (System.currentTimeMillis() - startTime));
        System.out.println("result:" + Arrays.toString(array));
    }

    public static void quickSort(int[] array){
        
    }


    /**
     * 堆排序
     */
    public void heapSort(int[] array) {
        //创建最大堆，从最后一个父节点开始，依次下沉
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
//        System.out.println(Arrays.toString(array));
        for (int i = array.length - 1; i >= 0; i--) {
            //取出数组第一个值，并放值数组末尾（第一个值为最大值）
            int temp = array[i]; //临时存储末尾的值
            array[i] = array[0];
            array[0] = temp;
            downAdjust(array, 0, i);
        }

    }

    //最大堆向下调整
    public void downAdjust(int[] array, int parentIndex, int length) {
        int childIndex = 2 * parentIndex + 1;
        int temp = array[parentIndex];
        while (childIndex < length) {
            if (childIndex + 1 < length) {
                childIndex = array[childIndex] > array[childIndex + 1] ? childIndex : childIndex + 1;
            }
            //如果 父节点 > 子节点 说明此节点已满足最大堆的特征，直接退出遍历
            if (temp > array[childIndex]) {
                break;
            }
            //否则 将子节点升为父节点，原父节点继续向下遍历
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;//将父节点指向当前的子节点
            childIndex = 2 * parentIndex + 1;   //子节点也重新赋值
        }
        array[parentIndex] = temp;
    }


    public static int[] gennerateArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }
}