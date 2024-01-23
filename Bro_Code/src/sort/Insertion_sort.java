package sort;

public class Insertion_sort {
    public static void main(String[] args) {
        int array[] = {9, 2, 3, 1, 4, 8, 5};
        insertion_sort(array);
        for (int nums : array){
            System.out.print(nums);
        }
    }

    private static void insertion_sort(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            int j = i + 1;
            int temp = array[j];
            if (array[i] > temp){
                
            }
        }
    }
}
