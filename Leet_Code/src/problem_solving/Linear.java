package problem_solving;
import java.util.Objects;

public class Linear {
    static void linear(int[] arr, int target){
        if (arr.length == 0){
            System.out.println("Please add the element...");
        } else if (Objects.isNull(arr)) {
            System.out.println("Don't set the null value....");
        }else {
            if (Objects.nonNull(arr)){
                for (int i = 0; i < arr.length; i++) {
                    if (target == arr[i]){
                        System.out.println(i);
                    }
                }
            }
        }

    }
    public static void main(String[] args) {
        int[] nums = null;
        int target = 90;
        linear(nums,target);
    }
}
