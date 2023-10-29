import java.util.Arrays;

public class Test {

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];
        int i = 0, j = 0, k = l;
        while ( i < n1) {
            L[i] = arr[k];
            i++;
            k++;
        }
        while ( j < n2) {
            R[j] = arr[k];
            j++;
            k++;
        }

        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {

            if (L[i] < R[j]) {
                arr[k] = L[i];
                k++;
                i++;
            } else {
                arr[k] = R[j];
                k++;
                j++;
            }
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {3, 2, 1};

        mergeSort(arr, 0, 2);
        System.out.println(Arrays.toString(arr));
    }


}