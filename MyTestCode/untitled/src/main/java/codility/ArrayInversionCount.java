package codility;

public class ArrayInversionCount {

    private int count = 0;
    public int solution(int[] A) {
        if (A ==null || A.length == 0) return 0;
        mergeSort(A, 0, A.length - 1);
        return this.count;
    }

    public void mergeSort(int[] nums, int l, int r) {
        if (l < r) {
            int m = l + (r - l)/2;
            mergeSort(nums, l, m);
            mergeSort(nums, m+1, r);
            merge(nums, l, m , r);
        }
    }

    public void merge(int[] nums, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        int local = 0;
        for (int i = 0; i < n1; i++) {
            L[i] = nums[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = nums[m + j + 1];
        }
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                nums[k] = L[i];
                i++;
                this.count += local;
                if (this.count > 1000000000) this.count = -1;
            } else {
                nums[k] = R[j];
                j++;
                local++;
            }
            k++;
        }
        while ( i < n1) {
            nums[k] = L[i];
            i++;
            k++;
            this.count += local;
            if (this.count > 1000000000) this.count = -1;
        }
        while (j < n2){
            nums[k] = R[j];
            j++;
            k++;
        }

    }

    public static void main(String[] args) {
        System.out.println(new ArrayInversionCount().solution(new int[]{1,3,2}));
    }

}
