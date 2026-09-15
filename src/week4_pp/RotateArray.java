package week4_pp;

import java.util.Arrays;

public class RotateArray {
    static int[] rotateArray(int[] nums, int k) {
        k %= nums.length;
        int[] ans = new int[nums.length];

        for(int i=0;i<nums.length;i++)
            ans[(i+k)%nums.length]=nums[i];

        return ans;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(rotateArray(nums,3)));
    }
}