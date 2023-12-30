package com.bikerental;

import java.time.LocalDateTime;

public class BasicRentCalculationStrategy extends RentCalculationStrategy {
  @Override
  double getRent(Bike bike, Booking booking, LocalDateTime actualEndDate) {
    return 0;
  }

  @Override
  double getPenalty(Bike bike, Booking booking, LocalDateTime actualEndDate) {
    return 0;
  }
}
