package com.tt.Calculator;

public class Subtraction {
    private int num1;
    private int num2;
    private int result;

    public Subtraction(int num1,int num2){
        this.num1=num1;
        this.num2=num2;
    }
    public void doOperation(){
        result=num1-num2;
    }
    public void printAnswer(){
        System.out.println (result);
    }
}
