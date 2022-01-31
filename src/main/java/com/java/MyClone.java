package com.java;

public class MyClone {

    public static void main(String[] args) throws CloneNotSupportedException {
        Data d = new Data();
       // System.out.println(System.identityHashCode(d));
        System.out.println(d);

      //  System.out.println(System.identityHashCode((Data)d.clone()));
        System.out.println(d.clone());
    }
}


class Data implements Cloneable {

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
