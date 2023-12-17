package com.parking.slotComaparator;

import com.parking.slot.ParkingSlot;

import java.util.Comparator;

public class ExitDistanceComparator {

  public static Comparator<ParkingSlot> getComparator( ){
    Comparator<ParkingSlot> defaultComparator = Comparator.comparingInt(ParkingSlot::getDistanceFromExit).reversed();
    return defaultComparator;
  }


}
