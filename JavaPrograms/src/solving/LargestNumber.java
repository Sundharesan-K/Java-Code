package solving;

import java.util.Scanner;

public class LargestNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.println ("Enter the first Number");
        int a=scanner.nextInt ();
        System.out.println ("Enter the Second Number");
        int b=scanner.nextInt ();
        System.out.println ("Enter the Third Number");
        int c=scanner.nextInt ();

        if (a>b && a>c){
            System.out.println (a+" largest Number");
        }
        else if (b>a && b>c){
            System.out.println (b+" largest Number");
        }
        else if (c>a && c>b){
            System.out.println (c+" largest Number");
        }
        int largest=c>(a>b?a:b)?c:(a>b?a:b);
        System.out.println (largest);
    }
}
