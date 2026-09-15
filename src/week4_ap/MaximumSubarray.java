package week4_ap;

public class MaximumSubarray {
    static int maxSubArray(int[] nums) {
        int current=nums[0], max=nums[0];

        for(int i=1;i<nums.length;i++){
            current=Math.max(nums[i], current+nums[i]);
            max=Math.max(max,current);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}