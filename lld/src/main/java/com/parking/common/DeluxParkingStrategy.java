package com.parking.common;

import com.parking.slotCalculation.NearestToElevatorSlotSTrategy;
import com.parking.slotCalculation.SlotCalculationStrategy;
import com.parking.tariff.ConvinienceTarrifCalculationStrategy;
import com.parking.tariff.TariffCalculationStrategy;

public class DeluxParkingStrategy extends ParkingStrategy{
  public DeluxParkingStrategy() {
    super();
    TariffCalculationStrategy tariffCalculationStrategy = new ConvinienceTarrifCalculationStrategy();
    SlotCalculationStrategy slotCalculationStrategy = new NearestToElevatorSlotSTrategy();
    super.setSlotCalculationStrategy(slotCalculationStrategy);
    super.setTariffCalculationStrategy(tariffCalculationStrategy);
  }
}
