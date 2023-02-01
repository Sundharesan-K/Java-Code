package solving;

public class Swapping {
    public static void main(String[] args) {
        int a=67,b=78;
        int c;
        System.out.println (a + " " + b);
        c=a;
        a=b;
        b=c;
        System.out.println (a + " " + b);
        a=a+b;
        b=a-b;
        a=a-b;
        System.out.println (a + " " + b);
    }
}
