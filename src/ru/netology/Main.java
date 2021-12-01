package ru.netology;


import ru.netology.Bill.Bill;
import ru.netology.TaxService.TaxService;
import ru.netology.taxes.IncomeTaxType.IncomeTaxType;
import ru.netology.taxes.ProgressiveTaxType.ProgressiveTaxType;
import ru.netology.taxes.VATaxType.VATaxType;

import java.math.BigDecimal;

class Main {

    public static void main(String[] args) {

        TaxService taxService = new TaxService();

        Bill[] payments = new Bill[]{

                new Bill(BigDecimal.valueOf(510_000_000), new IncomeTaxType(), taxService),
                new Bill(BigDecimal.valueOf(10_000_000), new ProgressiveTaxType(), taxService),
                new Bill(BigDecimal.valueOf(10_000), new VATaxType(), taxService),
                new Bill(BigDecimal.valueOf(2101), new ProgressiveTaxType(), taxService)};

        for (Bill bill : payments) {

            bill.payTaxes();
        }

    }
}