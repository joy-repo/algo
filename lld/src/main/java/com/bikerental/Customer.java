package com.bikerental;

import lombok.Data;

@Data
public class Customer {

  private String name;
  private String licenseID;
  private byte[] licenceImage;

}
