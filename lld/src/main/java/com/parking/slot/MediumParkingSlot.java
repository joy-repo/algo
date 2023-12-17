package com.parking.slot;

public class MediumParkingSlot extends ParkingSlot{

  public MediumParkingSlot(String parkingName, int parkingId,int distanceFromElevator, int distanceFromExit){
    super(parkingName, parkingId,distanceFromElevator, distanceFromExit);
    setParkingSize();
  }


  public void setParkingSize() {
    super.setParkingSize(ParkingSize.MEDIUM);
  }
}
