package com.java.designpattern.observer;

import lombok.Data;

@Data
public class Store {

  private NotificationService notificationService;

  public Store(){
    this.notificationService=new NotificationService();
  }
}
