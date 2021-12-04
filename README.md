## Задача 2. Банковские счета

### Описание
Часто в проектировании программ нам удобно опираться на понятия, которые не представлены в реальном мире,
но служат удобной "опорой" для объединения нескольких классов.

Так, например, в банковском деле нет абстрактного понятия "Счет". Каждый счет в банке имеет четкое назначение: сберегательный, кредитный, расчетный.
Но банковская программа работает с общими для счетов операциями как с одинаковыми объектами, и выполняет их, обращаясь к общему типу "Счет",
хотя его и невозможно явно инстанцировать в программе. Реализуйте этот сценарий, опираясь на механизмы полиморфизма.

### Функционал программы
1. Были созданы несколько классов — различных счетов на основе общего абстрактного класса `AccountImpl`, реализующего интерфейс `Account`.
- Интерфейс `Account` Предоставляет возможный функционал абстрактного "Счета"
```java
public interface Account {

    void pay(int amount);

    void transfer(Enlargeable account, int amount) throws EnlargeException;

    void addMoney(int amount) throws EnlargeException;

    void printBalance();

    int getBalance();
}
```
- Так как все операции связанны либо с увеличением счета, либо с уменьшением, созданы два абстрактных метода: `enlarge` и `reduce`, которые будут реализованы в классах наследниках
```java
abstract protected int enlarge(final int amount);

```
```java
abstract protected int reduce(final int amount);
```
- абстрактный класс `AccountImpl`
```java
public abstract class AccountImpl implements Account {

    abstract protected int enlarge(final int amount);
    abstract protected int reduce(final int amount);
    
    @Override
    public void printBalance() {
        System.out.printf("Баланс вашего лицевого счета: %d руб.\n", getBalance());
    }

    @Override
    public void pay(int amount) {

        try {
            reduce(amount);
            printPayMsg(amount);
        } catch (ReduceException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void transfer(AccountImpl account, int amount) {

        try {
            reduce(amount);
            try {
                account.enlarge(amount);
                printSuccessTransferMsg(amount);
            } catch (EnlargeException exception) {
                System.out.println(exception.getMessage());
                enlarge(amount);
                printFailTransferMsg();
            }
        } catch (ReduceException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void addMoney(int amount) {

        try {
            enlarge(amount);
            printAddMoneyMsg(amount);
        } catch (EnlargeException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void printPayMsg(final int payment) {

        System.out.printf("Вы совершили покупку на %d руб. Баланс: %d руб.\n", payment, getBalance());
    }

    private void printSuccessTransferMsg(final int transfer) {

        System.out.printf("Вы перевели %d руб. Баланс: %d\n", transfer, getBalance());
    }

    private void printFailTransferMsg() {

        System.out.printf("Перевод отменен, деньги возвращены на ваш счет. Баланс: %d руб.\n", getBalance());
    }

    private void printAddMoneyMsg(final int money) {

        System.out.printf("На ваш счет зачислено %d руб. Баланс: %d руб.\n", money, getBalance());
    }
}
```
2. В абстрактном классе реализованны основные методы задания `pay(int amount)`, `transfer(Account account, int amount)`, `addMoney(int amount)`. Но логику их выполнения, связанную с изменением баланса, будут контролировать классы наследники, потому что они сами "знают" когда его можно менять, а когда нельзя. Так же для Сберегательного счета переопределен метод `pay` так как с него нельзя платить. 
- Сберегательный счет (`SavingsAccount`)
```java
public class SavingsAccount extends AccountImpl {

    private int account;

    public SavingsAccount(int account) {
        this.account = account;
    }

    @Override
    public int getBalance() {
        return account;
    }

    @Override
    public int enlarge(int amount) {
        return account += amount;
    }

    @Override
    public int reduce(int amount) {

        int temp = getBalance();
        if (temp - amount >= 0) {

            return account -= amount;
        } else {
            throw new ReduceException(String.format("Операция отклонена, баланс вашего счета: %d руб.", account));
        }
    }

    @Override
    public void pay(int amount) {
        System.out.println("Операция отклонена, нельзя совершать покупки со Сберегательного счета!");
    }
}
```
- Кредитный счет (`CreditAccount`)
```java
public class CreditAccount extends AccountImpl {

    private int account;

    public CreditAccount(int account) {
        this.account = account;
    }

    @Override
    public int getBalance() {
        return account;
    }

    @Override
    public int enlarge(int amount) {

        int temp = getBalance();
        if (temp + amount <= 0) {

            return account += amount;
        } else {
            throw new EnlargeException("Операция отклонена, баланс кредитной карты не может быть больше 0!");
        }
    }

    @Override
    public int reduce(int amount) {
        return account -= amount;
    }
}
```
- Расчетный счет (`CheckingAccount`)
```java
public class CheckingAccount extends AccountImpl {

    private int account;

    public CheckingAccount(int account) {
        this.account = account;
    }

    @Override
    public int getBalance() {
        return account;
    }

    @Override
    public int enlarge(int amount) {

        return account += amount;
    }

    @Override
    public int reduce(int amount) {

        int temp = getBalance();
        if (temp - amount >= 0) {

            return account -= amount;
        } else {
            throw new ReduceException(String.format("Операция отклонена, баланс вашего счета: %d руб.", account));
        }
    }
}
```
3. Так как задача усложненная, для удобства контроля возможности совершить операцию по счету, созданы два класса Исключений, которые будут "бросаться" при невозможности выполнения операций уменьшения или увеличения баланса счета. Это особенно удобно для метода `transfer` потому, что в этом методе, нужно выполнить сразу две операции: снять деньги с одного счета и добавить к другому. Если пополняемый счет кредитный и его баланс после пополнения будет больше 0, операция должна быть отклонена, а это реализовать обычным методом очень не красиво.