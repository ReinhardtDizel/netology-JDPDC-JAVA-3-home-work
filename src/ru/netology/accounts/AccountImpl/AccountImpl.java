package ru.netology.accounts.AccountImpl;

import ru.netology.exceptions.EnlargeException.EnlargeException;
import ru.netology.exceptions.ReduceException.ReduceException;
import ru.netology.accounts.Account;

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
