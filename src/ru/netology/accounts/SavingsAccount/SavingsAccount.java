package ru.netology.accounts.SavingsAccount;

import ru.netology.accounts.AccountImpl.AccountImpl;
import ru.netology.exceptions.ReduceException.ReduceException;

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
    protected int enlarge(int amount) {
        return account += amount;
    }

    @Override
    protected int reduce(int amount) {

        int temp = account;
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
