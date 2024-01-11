package data_structure.array;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.add(90);
        dynamicArray.add(12);
        dynamicArray.add(89);
        dynamicArray.add(209);
        dynamicArray.add(67);
        dynamicArray.add(2);
        dynamicArray.add(9878);
        dynamicArray.insert(1,1);
        dynamicArray.delete(12);
        System.out.println(dynamicArray);
        System.out.println("Size : "+dynamicArray.size);
        System.out.println("Capacity : "+dynamicArray.capacity);
        System.out.println("Empty : "+dynamicArray.isEmpty());

    }
}
