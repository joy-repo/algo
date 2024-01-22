package com.java.designpattern.observer;

public class EmailListner implements EventListner{

  private String customerName;

  public EmailListner(String customerName){
    this.customerName=customerName;
  }
  @Override
  public void update() {
    System.out.println("Notify Email: "+ customerName);
  }
}
