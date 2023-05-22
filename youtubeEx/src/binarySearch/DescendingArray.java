package binarySearch;

public class DescendingArray {
    public static void main(String[] args) {
//        int[] arr = {89, 78, 68, 59, 45, 33, 26, 10, 6};
        int[] arr = {};
        int ans = descending (arr, 48);
        System.out.println (ans);
    }
    static int descending(int[] arr, int target){
        if (arr.length==0){
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        boolean isAsc = arr[start] < arr[end];
        while (start <= end){
            int mid = start + (end - start) / 2;

            if (target == arr[mid]){
                return mid;
            }
            if (isAsc){
                if (target < arr[mid]){
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }else {
                if (target > arr[mid]){
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
}
