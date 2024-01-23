package sort;

import java.util.Arrays;
import java.util.List;

public class Selection_sort {
    public static void main(String[] args) {
        int array[] = {9, 2, 3, 1, 4, 8, 5};
        selection_sort(array);
        for (int nums : array){
            System.out.print(nums);
        }
    }
    private static void selection_sort(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]){
                    min = j;
                }
            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
}
