package problem_solving;

public class BubbleSort {
    static int bubble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println();
            for (int num : arr) {
                System.out.print(num + " ");
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {7, 3, 2, 6, 4, 5, 1};
        bubble(nums);

    }
}
