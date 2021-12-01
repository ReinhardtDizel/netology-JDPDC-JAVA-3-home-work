package ru.netology.taxes.IncomeTaxType;

import ru.netology.taxes.TaxType.TaxType;

import java.math.BigDecimal;

public class IncomeTaxType extends TaxType {

    private final BigDecimal taxRate = BigDecimal.valueOf(0.13);

    @Override
    public BigDecimal calculateTaxFor(BigDecimal amount) {
        return amount.multiply(taxRate);
    }
}
