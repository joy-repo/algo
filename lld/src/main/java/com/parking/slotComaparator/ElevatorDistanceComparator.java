package com.parking.slotComaparator;

import com.parking.slot.ParkingSlot;

import java.util.Comparator;

public class ElevatorDistanceComparator extends SlotComparator {

  public static Comparator<ParkingSlot> getComparator( ){
    Comparator<ParkingSlot> elevatorDis = Comparator.comparingInt(ParkingSlot::getDistanceFromElevator).reversed();
    return elevatorDis;
  }
}
