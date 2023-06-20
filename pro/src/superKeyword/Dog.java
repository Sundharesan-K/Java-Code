package superKeyword;

public class Dog extends Animal {
    void eat(){
        System.out.println ("sleeping");
    }
    void brake(){
        System.out.println ("braking");
    }
    void work(){
       super.eat ();
        brake ();
        eat ();

    }
}
