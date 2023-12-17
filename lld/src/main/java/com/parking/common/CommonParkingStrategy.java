package com.parking.common;

import com.parking.slotCalculation.EconomicSlotCalculationStrategy;
import com.parking.slotCalculation.NearestToElevatorSlotSTrategy;
import com.parking.slotCalculation.SlotCalculationStrategy;
import com.parking.tariff.ConvinienceTarrifCalculationStrategy;
import com.parking.tariff.EconomicTarrifCalcutaionStrategy;
import com.parking.tariff.TariffCalculationStrategy;

public class CommonParkingStrategy extends ParkingStrategy{

  public CommonParkingStrategy() {
    super();
    TariffCalculationStrategy tariffCalculationStrategy = new EconomicTarrifCalcutaionStrategy();
    SlotCalculationStrategy slotCalculationStrategy = new EconomicSlotCalculationStrategy();
    super.setSlotCalculationStrategy(slotCalculationStrategy);
    super.setTariffCalculationStrategy(tariffCalculationStrategy);
  }
}
