package week4_pp;

import java.util.Arrays;

public class MergeTwoSortedArrays {
    static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int i=0,j=0,k=0;

        while(i<a.length && j<b.length)
            res[k++] = (a[i] < b[j]) ? a[i++] : b[j++];

        while(i<a.length) res[k++] = a[i++];
        while(j<b.length) res[k++] = b[j++];

        return res;
    }

    public static void main(String[] args) {
        int[] a={1,3,5}, b={2,4,6};
        System.out.println(Arrays.toString(mergeSortedArrays(a,b)));
    }
}