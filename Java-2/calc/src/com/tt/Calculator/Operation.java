package com.tt.Calculator;

import java.util.Scanner;

public class Operation {
    Scanner scan = new Scanner (System.in);
    public int num1;
    public int num2;
    private int option;
    private char Operator;
    private char[] Operators={'+','-','*','/'};

    public void wel(){
        System.out.println ("WELCOME");
    }

    public void display() {
        System.out.println ("Enter the Option");

        System.out.println ("1.Addition ");
        System.out.println ("2.Subtraction ");
        System.out.println ("3.Multiplication ");
        System.out.println ("4.Division");

        option = scan.nextInt ();
    }

    public void Values() {
        System.out.println ("Enter the first number");
        num1 = scan.nextInt ();
        System.out.println ("Enter the second number");
        num2 = scan.nextInt ();
    }

    public void getNumber() {
        if (option == 1) {
            System.out.println ("Addition");
        } else if (option == 2) {
            System.out.println ("Subtraction");
        } else if (option == 3) {
            System.out.println ("Multiplication");
        } else if (option == 4) {
            System.out.println ("Division");
        } else {
            System.out.println ("Error");
        }
    }
    public void operator(){
        int op = Operators[option - 1];
        switch( op) {
            case '+':
                Addition addOp = new Addition (num1, num2);
                addOp.doOperation ();
                addOp.printAnswer ();
                break;
            case '-':
                Subtraction subop = new Subtraction (num1, num2);
                subop.doOperation ();
                subop.printAnswer ();
                break;
            case '*':
                Multiplication mulop = new Multiplication (num1, num2);
                mulop.doOperation ();
                mulop.printAnswer ();
                break;
            case '/':
                Division diviop = new Division (num1, num2);
                diviop.doOperation ();
                diviop.printAnswer ();
                break;

        }



    }
}
