package com.java;

import java.io.IOException;
import java.util.List;

public class Inheritance {

    public static void main(String[] args) {
        Child p = new Child("g");
//        p.parentFunc();
//        System.out.println("STRING - "+ p.pString);
    }

}

class Emp {}

class Manager extends Emp{}



class Parent {

    public Parent(String s){
        System.out.println("PARENT begin");
        parentFunc();
        System.out.println("PARENT END");
    }

    public String pString = "parent";

    public Emp parentFunc()  throws RuntimeException{
        System.out.println("PARENT- parentFunc");
        System.out.println("PARENT-pString :"+ pString);
        return  null;
    }
}

class Child extends Parent{

    public Child(String s){
        super("hj");
        System.out.println("Child Begin");
        parentFunc();
        System.out.println("Child End");
    }

    public String pString = "child";

    @Override
    public Manager parentFunc() {
        System.out.println("CHILD- parentFunc");
        System.out.println("CHILD-pString :"+ pString);
        return null;
    }
}
