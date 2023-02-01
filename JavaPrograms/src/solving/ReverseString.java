package solving;

public class ReverseString {
    public static void main(String[] args) {
        String str="TrusTrace";
        String rev="";

        int len=str.length ();
        for (int i=len-1;i>=0;i--){
            rev=rev+str.charAt (i);
        }
        System.out.println ("Reverse String are :" + rev);

        StringBuffer buffer=new StringBuffer (str);
        System.out.println (buffer.reverse ());

    }
}
