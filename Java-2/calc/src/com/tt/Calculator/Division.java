package com.tt.Calculator;

public class Division {
    private int num1;
    private int num2;
    private int result;

    public Division(int num1,int num2){
        this.num1=num1;
        this.num2=num2;
    }
    public void doOperation(){
        result=num1/num2;
    }
    public void printAnswer(){
        System.out.println (result);
    }
}
