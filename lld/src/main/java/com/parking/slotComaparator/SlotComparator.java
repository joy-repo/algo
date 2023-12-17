package com.parking.slotComaparator;

import com.parking.slot.ParkingSlot;

import java.util.Comparator;

public class SlotComparator {

  public static Comparator<ParkingSlot> getComparator( ){
    Comparator<ParkingSlot> elevatorDis = Comparator.comparingInt(ParkingSlot::getParkingId).reversed();
    return elevatorDis;
  }
}
