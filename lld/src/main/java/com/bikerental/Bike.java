package com.bikerental;

import lombok.Data;

@Data
public abstract class Bike {

  private String bikeID;
  private BikeStatus status;
  private Station station;

}
