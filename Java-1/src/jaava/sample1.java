package jaava;

import condition.Sample;

public class sample1 {

    public static void main(String[] args) {
        String email = "sundhar";
        Sample sample = new Sample ();
        if (sample.getEmail ()==email){
            System.out.println ("If Block");
        }else {
            System.out.println ("Else Block");
        }
    }

}
