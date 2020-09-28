package com.my.cas2;

public class Demo {
    public static void main(String[] args) {
         int[] nums = new int[]{1,3,5,8,9,2,4,6,7,10,15,12,20};

         mergeSort(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "       ");
        }

    }

    public static void mergeSort(int[] array , int L,int R) {
        if (L == R) {
            return;
        }

        // extrac Middle value
        int M = (R + L) / 2;

        //left sort
        mergeSort(array, 0,L);
        //right sort
        mergeSort(array, M+1, R);
        // left sort and right sort merge
        merged(array, L, M+1, R);
    }

    public static void merged(int[] array,int L,int M,int R) {
        //left arrayx
        int[] leftArray = new int[M - L];

        // right arry
        int[] rightArray = new int[R - M + 1];

        // fill data
        for (int i = L; i < M ; i++) {
            leftArray[i-L] = array[i];
        }

        for (int i =  M ; i <= R; i++) {
            rightArray[i - M] = array[i];
        }

        int i = 0;
        int j = 0;
        int k = L;

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] < rightArray[j]) {
                array[k] = leftArray[i];
                k++;
                i++;
            } else {
                array[k] = rightArray[j];
                k++;
                j++;
            }
        }
        // 左边数组 还没完，右边已经完了
        while (i < leftArray.length) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArray.length) {
            array[k] = leftArray[j];
            j++;
            k++;
        }

    }
}
