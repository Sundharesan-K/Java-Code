package com.tt.learning;

import java.util.Scanner;

public class Operator {
    Scanner scan = new Scanner (System.in);
    int x;
    int y;

    String result;

    void ScanOp() {
        System.out.println ("Enter the x values ");
        x = scan.nextInt ();
        System.out.println ("Enter the y values ");
        y = scan.nextInt ();
    }


    void Ifclass() {
        if (x < y) {
            System.out.println ("true");
        } else {
            System.out.println ("False");
        }
    }

    void ShortHand() {
        String result = (x > y) ? "Correct" : "Wrong";
        System.out.println (result);
    }

    void cases() {
        int day = 2;
        switch (day) {
            case 1: {
                System.out.println ("Monday");
            }
            case 2: {
                System.out.println ("Tuesday");
                break;
            }
            case 3: {
                System.out.println ("Wednesday");
            }
        }
    }
    static void Speed(int maxSpeed ){
        System.out.println ("Car Speed :" + maxSpeed);
    }

}

