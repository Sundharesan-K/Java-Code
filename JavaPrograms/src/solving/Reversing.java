package solving;

import java.util.Scanner;

public class Reversing {
    public static void main(String[] args) {
        System.out.println ("Enter the Number ");
        Scanner scan = new Scanner (System.in);
        int num = scan.nextInt ();

        int rev = 0;
        while (num != 0) {
            rev = rev * 10 + num % 10;
            num = num / 10;
        }
        System.out.println (rev);
    }
}