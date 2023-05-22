package binarySearch2DArray;

import java.util.Arrays;

public class RowColMatrix {
    public static void main(String[] args) {
        int[][] array = {
                {23, 35, 37, 41},
                {36, 43, 56, 66},
                {44, 54, 65, 73}
        };
        System.out.println (Arrays.toString (rowCol (array,65)));
    }
    static int[] rowCol(int[][] array, int target){
        int r = 0;
        int c = array.length - 1;

        while (r < array.length && c >= 0) {
            if (array[r][c] == target) {
                return new int[]{r, c};
            }

            if (array[r][c] < target){
                r++;
            }else {
                c--;
            }
        }
        return new int[]{-1,-1};
    }
}
