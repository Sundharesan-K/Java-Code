package DataStructures;

import java.util.Arrays;

public class TheArrays {
    public static void main(String[] args) {
        String[] color=new String[5];
        color[0]="black";
        color[1]="blue";
        System.out.println (Arrays.toString (color));
        System.out.println (color[0]);
        System.out.println (color[1]);
        System.out.println (color[2]);
        System.out.println (color[3]);
        System.out.println (color[4]);

        int[] numbers={100,200};
        System.out.println ();

        for (String colors:color){
            System.out.println (colors);
        }
        Arrays.stream (color).forEach (System.out::println);
    }
}
