package com.parking.terminals;

import com.parking.tariff.TariffCalculationStrategy;
import com.parking.terminals.payments.PaymentProcessor;
import lombok.Data;

@Data
public abstract class Terminal {

  private PaymentProcessor paymentProcessor;





}
