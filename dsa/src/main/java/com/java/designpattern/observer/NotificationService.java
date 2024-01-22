package com.java.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {

  private List<EventListner> customers;

  public NotificationService(){
    customers= new ArrayList<>();
  }

  public void subscribeMobile(String customerName){
    MobileListner mobileListner= new MobileListner(customerName);
    customers.add(mobileListner);

  }

  public void subscribeEmail(String customerName){
    EmailListner emailListner= new EmailListner(customerName);
    customers.add(emailListner);

  }

  public void notifyCustomer(){

    customers.forEach(c -> c.update());

  }
}
