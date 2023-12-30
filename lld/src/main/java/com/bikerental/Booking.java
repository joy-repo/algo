package com.bikerental;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {

  private Customer customer;
  private Bike bike;

  private LocalDateTime startDate;
  private LocalDateTime endDate;

  private double rentEstimate;
  private  double actualAmount;

  private PaymentStatus paymentStatus;

  private LocalDateTime actualEndDate;

  private double advancePaid;

  private RentCalculationStrategy rentCalculationStrategy;

  public void completePayment(PaymentStrategy paymentStrategy){

    double rent = rentCalculationStrategy.getRent(bike, this,actualEndDate );
    double penalty = rentCalculationStrategy.getPenalty(bike, this,actualEndDate );

    this.paymentStatus= paymentStrategy.completePayment(rent+penalty);
  }


}
