package com.java.designpattern.observer;

public class MobileListner implements EventListner{

  private String customerName;

  public MobileListner(String customerName){
    this.customerName=customerName;
  }
  @Override
  public void update() {
    System.out.println("Notify Mobile: "+ customerName);
  }
}
