package sort;

import java.util.Arrays;

public class MergeSort {


    public static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {

            int mid = low + (high - low) / 2;

            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high) {
        int len1 = mid - low + 1;
        int len2 = high - mid;

        int[] left = new int[len1];
        int[] right = new int[len2];

        for (int m = low, n = 0; m <= mid; m++, n++) {
            left[n] = arr[m];
        }
        for (int m = mid + 1, n = 0; m <= high; m++, n++) {
            right[n] = arr[m];
        }

        int i = 0;
        int j = 0;
        int k = low - 1;

        while (i < len1 && j < len2) {
            k++;
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
        }
        if (i >= len1) {
            while (j < len2) {
                k++;
                arr[k] = right[j];
                j++;
            }
        }
        if (j >= len2) {
            while (i < len1) {
                k++;
                arr[k] = left[i];
                i++;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[] {5, 6, 2, 3, 7, 9, 10, 1, 0, -1};

        mergeSort(arr, 0, arr.length - 1);

        Arrays.stream(arr).forEach(System.out::println);

    }


}
