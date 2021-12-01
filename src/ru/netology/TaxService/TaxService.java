package ru.netology.TaxService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxService {
    public void payOut(BigDecimal taxAmount) {
        System.out.format("Уплачен налог в размере %.2f%n", taxAmount.setScale(3, RoundingMode.CEILING));
    }
}
