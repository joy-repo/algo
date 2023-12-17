package com.parking.tariff;

import com.parking.ticket.ParkingTicket;

import java.time.LocalDateTime;

public abstract class TariffCalculationStrategy {

  public abstract double calculateTariff(ParkingTicket parkingTicket, LocalDateTime exitTime);
}
