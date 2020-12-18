package com.example.myapplication;

import android.util.Log;

import org.junit.Test;

import java.util.Arrays;

public class BinaryHeap {

    @Test
    public void test() {
        /**       1
         *      3   2
         *   6   5 7  8
         * 9  10
         *
         *            0
         *       1         2
         *    6     3   7    8
         *  9  10 5
         * 左孩子的下标为 2 * parent + 1
         * 右下标的孩子为 2 * parent + 2
         *
         */
        System.out.println("test start");
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 上浮调整（自己实现）
     *
     * @param array 待调整待堆
     */
    public static void upAdjust(int[] array) {
        long startTime = System.nanoTime();
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;
        while (childIndex > 0 && array[childIndex] < array[parentIndex]) {

            int temp = array[parentIndex];
            array[parentIndex] = array[childIndex];
            array[childIndex] = temp;

            childIndex = parentIndex;
            System.out.println("childindex:" + childIndex);
            parentIndex = (childIndex - 1) / 2;
            System.out.println(parentIndex);
        }
        System.out.println("upAdjust1 endTime:" + (System.nanoTime() - startTime));

    }

    //推荐写法
    public static void upAdjust1(int[] array) {
        long startTime = System.nanoTime();
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;
        int temp = array[childIndex];
        //此处永远会和temp比较（即新插入的值，所以下方不需要真正替换，节省开销）
        while (childIndex > 0 && temp < array[parentIndex]) {
            //无须真正交换，单向赋值即可
            array[childIndex] = array[parentIndex];    //将子节点的值替换成父节点的值
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;    //parent赋值为上一个节点
        }
        array[childIndex] = temp;
        System.out.println("upAdjust1 endTime:" + (System.nanoTime() - startTime));
    }

    /**
     * 下沉调整
     *
     * @param array       待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        int childIndex = 2 * parentIndex + 1;
        int temp = array[parentIndex];
        while (childIndex < length) {
            if (childIndex + 1 < length) {
                childIndex = array[childIndex] < array[childIndex + 1] ? childIndex : childIndex + 1;
            }
            if (temp <= array[childIndex]) {
                break;
            }

            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;

        }
        array[parentIndex] = temp;
    }

    /**
     * 构建最小堆
     *
     * @param array
     */
    public static void buildHeap(int[] array) {
        //从最后一个非叶子节点开始依次下沉调整
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }

    }

    /**
     * 堆排序
     *
     * @param array
     */
    public static void heapSort(int[] array) {
        //从最后一个非叶子节点开始依次下沉调整
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
        //循环删除堆顶元素，移至集合尾部，调整堆产生新的堆顶
        for (int i = array.length - 1; i > 0; i--) {
            System.out.println("ele:"+array[0]);
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            downAdjust(array, 0, i);
        }
    }

}
