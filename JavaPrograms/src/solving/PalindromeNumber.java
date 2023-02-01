package solving;

import java.util.Scanner;

public class PalindromeNumber {
    public static void main(String[] args) {
        System.out.println ("Enter the Number ");
        Scanner scan=new Scanner (System.in);
        int num = scan.nextInt ();
        int orgNum=num;
        int rev=0;
        while (num !=0){
            rev=rev*10+num%10;
            num=num/10;
        }
        if(orgNum==rev){
            System.out.println (orgNum + " Palindrome");
        }else {
            System.out.println (orgNum + " Not a Palindrome");
        }
    }
}
