package ru.netology.taxes.VATaxType;

import ru.netology.taxes.TaxType.TaxType;

import java.math.BigDecimal;

public class VATaxType extends TaxType {

    private final BigDecimal taxRate = BigDecimal.valueOf(0.18);

    @Override
    public BigDecimal calculateTaxFor(BigDecimal amount) {
        return amount.multiply(taxRate);
    }
}