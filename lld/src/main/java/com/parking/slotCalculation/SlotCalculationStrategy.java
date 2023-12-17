package com.parking.slotCalculation;

import com.parking.slot.ParkingSize;
import com.parking.slot.ParkingSlot;

public abstract class SlotCalculationStrategy {

 // private

  public abstract ParkingSlot getParkingSlot(ParkingSize parkingSize);

}
