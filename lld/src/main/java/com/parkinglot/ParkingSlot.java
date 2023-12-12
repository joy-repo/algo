package com.parkinglot;

import lombok.Data;

@Data
public class ParkingSlot {

      private String parkingName;
      private int parkingId;

      private ParkingSize parkingSize;

}
