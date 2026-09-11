package assignment_problems;

import java.util.Arrays;

public class RotateArray {
    static int[] rotateArray(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        int[] temp = new int[n];

        for (int i = 0; i < n; i++)
            temp[(i + k) % n] = nums[i];

        return temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(rotateArray(arr, 3)));
    }
}
