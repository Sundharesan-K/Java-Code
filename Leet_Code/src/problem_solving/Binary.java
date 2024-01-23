package problem_solving;

public class Binary {
    static int binary(int[] nums, int target){
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (target < nums[mid]){
                end = mid - 1;
            } else if (target == nums[mid]) {
                return mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {2,4,6,23,45,90};
        int target = 45;
        System.out.println(binary(nums,target));
    }
}
