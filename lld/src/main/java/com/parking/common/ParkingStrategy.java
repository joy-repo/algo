package com.parking.common;

import com.parking.slotCalculation.SlotCalculationStrategy;
import com.parking.tariff.TariffCalculationStrategy;
import lombok.Data;

@Data
public abstract class ParkingStrategy {

  private TariffCalculationStrategy tariffCalculationStrategy;
  private SlotCalculationStrategy slotCalculationStrategy;


}
