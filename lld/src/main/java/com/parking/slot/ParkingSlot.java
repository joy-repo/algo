package com.parking.slot;


import lombok.Data;

@Data
public abstract class ParkingSlot {

      private String parkingName;
      private int parkingId;

      private ParkingSize parkingSize;

      private int distanceFromElevator;
      private int distanceFromExit;


      public ParkingSlot(String parkingName, int parkingId, int distanceFromElevator, int distanceFromExit){
            this.parkingId=parkingId;
            this.parkingName=parkingName;
            this.distanceFromElevator=distanceFromElevator;
            this.distanceFromExit=distanceFromExit;
      }

}
