package com.parking.slotComaparator;

import com.parking.slot.ParkingSlot;

import java.util.Comparator;

public class ElevatorExitDistanceComparator  extends SlotComparator {

  public static Comparator<ParkingSlot> getComparator( ){
    Comparator<ParkingSlot> exit =ExitDistanceComparator.getComparator();
    Comparator<ParkingSlot> elevator = ExitDistanceComparator.getComparator();

    return elevator.thenComparing(exit);

//   Comparator<ParkingSlot> resComp = (p1,p2)->{
//     int i =exit.compare(p1,p2);
//     if(i==0) return elevator.compare(p1,p2);
//     return i;
//
//   };
//
//
//    return resComp;

  }
}
