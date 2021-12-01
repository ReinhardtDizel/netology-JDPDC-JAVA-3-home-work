## Задача 2. Задача от бухгалтеров

### Описание
Следующая задача пришла от наших бухгалтеров.
Бухгалтерская программа должна уметь проводить операции c различными агентами, как c физическими/юридическими лицами, так и с иностранными компаниями: чп, ип, ооо, зао, ~~иклмн~~, ~~ёпрст~~.
С некоторых операций налог платить не нужно, некоторые облагаются подоходным налогом, с некоторых необходимо уплатить НДС.
Необходимо расширить функциональность класса `Bill` возможностью работы с различными системами налогообложения.

### Функционал программы

→ Создание нескольких счетов и расчет налогов для них.

### Процесс реализации
1. Класс `Bill`

В системе уже есть класс `Bill`, в который мы добавили поле `TaxType taxType;` и метод `payTaxes()`:

```java
public class Bill {

  private BigDecimal amount;
  private TaxType taxType;
  private TaxService taxService;

  public Bill(BigDecimal amount, TaxType taxType, TaxService taxService) {
    this.amount = amount;
    this.taxType = taxType;
    this.taxService = taxService;
  }

  public void payTaxes() {

    BigDecimal taxAmount = taxType.calculateTaxFor(amount);

    taxService.payOut(taxAmount);
  }
}
```

А также класс налоговой службы:
```java
public class TaxService {
  public void payOut(BigDecimal taxAmount) {
    System.out.format("Уплачен налог в размере %.2f%n", taxAmount.setScale(3, RoundingMode.CEILING));
  }
}
```

2. Создадим классы для различных типов налогообложения.
* Базовый класс
```java 
public class TaxType {

    public BigDecimal calculateTaxFor(BigDecimal amount) {
        return null;
    }
}
```
* Классы, расширяющие `TaxType`:
  * Подоходный налог, = 13% (`IncomeTaxType`)
  * НДС, = 18% (`VATaxType`)
  * Прогрессивный налог, до 100 тысяч = 10%, больше 100 тысяч = 15% (`ProgressiveTaxType`)

3. В методе `main` создадим несколько счетов и оплатим с них налоги в налоговую службу.

```java
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
```