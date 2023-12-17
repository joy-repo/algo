package com.parking.tariff;

import com.parking.ticket.ParkingTicket;

import java.time.LocalDateTime;

public class ConvinienceTarrifCalculationStrategy extends TariffCalculationStrategy{

  @Override
  public double calculateTariff(ParkingTicket parkingTicket, LocalDateTime exitTime) {
    return 0;
  }
}
