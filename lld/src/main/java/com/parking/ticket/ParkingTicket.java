package com.parking.ticket;

import com.parking.common.ParkingStrategy;
import com.parking.slot.ParkingSlot;
import com.parking.tariff.TariffCalculationStrategy;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ParkingTicket {

  private String registrationId;
  private String ticketId;

  private LocalDateTime createdTime;
  private LocalDateTime entryTime;
  private LocalDateTime exitTime;

  private ParkingSlot parkingSlot;

  private ParkingStrategy parkingStrategy;



}
