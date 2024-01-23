package problem_solving;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class Two_Sum {
    public static int[] twoSum(int[] num,int target){
        LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < num.length; i++){
            int complement = target - num[i];
            if (linkedHashMap.containsKey(complement)){
                return new int[]{linkedHashMap.get(complement),i};
            }
            linkedHashMap.put(num[i],i);
        }
        return new int[]{-1,-1};
    }
    public static void main(String[] args) {
        int[] num = {25,98,56,45};
        int target = 70;
        System.out.println(Arrays.toString(twoSum(num,target)));

    }
}