package ru.netology.taxes.ProgressiveTaxType;

import ru.netology.taxes.TaxType.TaxType;

import java.math.BigDecimal;

public class ProgressiveTaxType extends TaxType {

    private final BigDecimal taxLimit = BigDecimal.valueOf(100_000);
    private final BigDecimal taxRateBeforeLimit = BigDecimal.valueOf(0.1);
    private final BigDecimal taxRateAfterLimit = BigDecimal.valueOf(0.15);

    @Override
    public BigDecimal calculateTaxFor(BigDecimal amount) {

        if (amount.compareTo(taxLimit) > 0) {

            return amount.multiply(taxRateAfterLimit);
        } else {

            return amount.multiply(taxRateBeforeLimit);
        }
    }
}
