package cn.minqi.consumer.util;

import java.util.Arrays;

public class Test {


    public static void main(String[] args) {
        int[] array = new int[] {10, 2, 8, 4, 5,18};
        int[] arr = Arrays.copyOf(array, array.length);

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                }
            }
        }
        for (int a: arr) {
            System.out.println(a);
        }
    }

}
