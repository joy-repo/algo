package com.parking.terminals;

import com.parking.common.ParkingStrategy;
import com.parking.slot.ParkingSlot;
import com.parking.tariff.TariffCalculationStrategy;
import com.parking.ticket.ParkingTicket;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class EntryTerminal {

  public ParkingTicket createTicket(ParkingStrategy parkingStrategy, String reigstraionId){

    ParkingSlot parkingSlot = getParkingSlot(parkingStrategy);

    ParkingTicket parkingTicket = ParkingTicket.builder()
        .ticketId(UUID.randomUUID().toString())
        .createdTime(LocalDateTime.now())
        .parkingStrategy(parkingStrategy)
        .registrationId(reigstraionId)
        .parkingSlot(parkingSlot)
        .build();

    return parkingTicket;


  }

  private ParkingSlot getParkingSlot(ParkingStrategy parkingStrategy) {

  }

}
