package com.bikerental;

import java.time.LocalDateTime;

public abstract class RentCalculationStrategy {

   abstract double getRent(Bike bike, Booking booking, LocalDateTime actualEndDate);

   abstract double getPenalty(Bike bike, Booking booking, LocalDateTime actualEndDate);

}
