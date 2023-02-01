package solving;

import java.util.Scanner;

public class CountOfEvenOrOddNUm {
    public static void main(String[] args) {
        System.out.println ("Enter the Number ");
        Scanner scanner=new Scanner (System.in);
        int num=scanner.nextInt ();
        int even_con=0;
        int odd_con=0;
        while (num>0){
            int rem=num%10;
            if (rem%2==0){
                even_con++;
            }
            else {
                odd_con++;
            }
            num=num/10;
        }
        System.out.println ("Number of Even : "+even_con);
        System.out.println ("Number of Odd " +odd_con);
    }
}
