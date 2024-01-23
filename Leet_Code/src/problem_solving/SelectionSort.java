package problem_solving;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class SelectionSort {
    static void selection(int[] arr) {
        if (Objects.isNull(arr)){
            System.out.println("Array is null....");
        } else if (arr.length == 0) {
            System.out.println("Please add the elements...");
        } else if (arr.length == 1) {
            System.out.println(Arrays.toString(arr));
        } else {
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i] > arr[j]) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
                System.out.println();
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
            System.out.println(Arrays.toString(arr));
        }
    }
    public static void main(String[] args) {
        int[] arr = {7676,129398,45,90,12,98,6567};
        selection(arr);
        IntStream sorted = Arrays.stream(arr).sorted();
        System.out.println(Arrays.toString(sorted.toArray()));
    }
}
