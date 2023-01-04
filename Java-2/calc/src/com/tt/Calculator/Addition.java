package com.tt.Calculator;

public class Addition extends Operation{
    public int num1;
    public int num2;
    public int result;

    public Addition(int num1,int num2){
        this.num1=num1;
        this.num2=num2;
    }
    public void doOperation(){
        System.out.println ("Inside do operation");
        result=num1+num2;
    }
    public void printAnswer(){
        System.out.println ("Inside print answer");
        System.out.println (result);
    }
}
