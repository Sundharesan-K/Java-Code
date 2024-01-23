package problem_solving;

import java.util.Arrays;
import java.util.stream.IntStream;

public class InsertionSort {
    static void insert(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i-1;
            while (j >= 0 && arr[j] > key){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
            System.out.println();
            for (int num : arr){
                System.out.print(num +" ");
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {7, 3, 2, 6, 4, 5, 1};
        insert(arr);
        System.out.println(Arrays.toString(arr));
        IntStream sorted = Arrays.stream(arr).sorted();
        System.out.println(Arrays.toString(sorted.toArray()));
    }
}
