package com.bikerental;

public interface PaymentStrategy {

  PaymentStatus completePayment(double amount);


}
