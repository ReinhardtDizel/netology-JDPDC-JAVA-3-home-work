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
class Bill {
    private double amount;
    private TaxType taxType;
    private TaxService taxService;
    
    public Bill(double amount, TaxType taxType, TaxService taxService) {
        this.amount = amount;
        this.taxType = taxType;
        this.taxService = taxService;
    }
    
    public void payTaxes() {
        // TODO вместо 0.0 посчитать размер налога исходя из TaxType
        double taxAmount = 0.0;
        
        taxService.payOut(taxAmount);
    }
}
```

А также класс налоговой службы:
```java
class TaxService {
    public void payOut(double taxAmount) {
        System.out.format("Уплачен налог в размере %.2f%n", taxAmount);
    }
}
```

2. Создадим классы для различных типов налогообложения.
* Базовый класс
```java 
class TaxType {
    public double calculateTaxFor(double amount) {
        // TODO override me!
        return 0.0;
    }
}
```
* Классы, расширяющие `TaxType`:
  * Подоходный налог, = 13% (`IncomeTaxType`)
  * НДС, = 18% (`VATaxType`)
  * Прогрессивный налог, до 100 тысяч = 10%, больше 100 тысяч = 15% (`ProgressiveTaxType`)

3. В методе `main` создадим несколько счетов и оплатим с них налоги в налоговую службу.

```java
public static void main(String[] args) {
    TaxService taxService = new TaxService();
    Bill[] payments = new Bill[] {
        // TODO создать платежи с различным типами налогообложения
    };
    for (int i = 0; i < payments.length; ++i) {
        Bill bill = payments[i];
        bill.payTaxes();
    }
}
```