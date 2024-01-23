package problem_solving;

import java.util.Arrays;

public class OuickSort {
    public static void main(String[] args) {
        int[] nums = {7, 3, 2, 6, 4, 5, 1};
        quick(nums,0,nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    static void quick(int[] nums, int low, int high) {
        if (low<high) {
            int pivot = partition(nums, low, high);
            quick(nums, low, pivot - 1);
            quick(nums, pivot + 1, high);
        }
    }

    private static int partition(int[] nums, int low, int high) {
        int pi = nums[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (nums[j] < pi){
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[i+1];
        nums[i+1] = nums[high];
        nums[high] = temp;

        return i+1;
    }

}
