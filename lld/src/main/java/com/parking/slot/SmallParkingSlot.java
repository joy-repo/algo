package com.parking.slot;

public class SmallParkingSlot extends ParkingSlot{


  public SmallParkingSlot(String parkingName, int parkingId, int distanceFromElevator, int distanceFromExit){
    super(parkingName, parkingId,distanceFromElevator, distanceFromExit);
    setParkingSize();
  }



  public void setParkingSize() {
    super.setParkingSize(ParkingSize.SMALL);
  }

}
