package solving;

import java.util.Scanner;

public class CountOfNumber {
    public static void main(String[] args) {
        System.out.println ("Enter the Number ");
        Scanner scanner=new Scanner (System.in);
        int num= scanner.nextInt ();
        int count=0;

        while (num>0){
            num=num/10;
            count++;
        }
        System.out.println ("Number Of Digits : "+count);
    }
}
