package com.parking.slot;

public class BigParkingSlot extends ParkingSlot {

  public BigParkingSlot(String parkingName, int parkingId,int distanceFromElevator, int distanceFromExit){
    super(parkingName, parkingId, distanceFromElevator,  distanceFromExit);
    setParkingSize();
  }


  public void setParkingSize() {
    super.setParkingSize(ParkingSize.BIG);
  }
}
