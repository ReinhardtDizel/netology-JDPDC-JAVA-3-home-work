package ru.netology.accounts.CreditAccount;

import ru.netology.accounts.AccountImpl.AccountImpl;
import ru.netology.exceptions.EnlargeException.EnlargeException;

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
    protected int enlarge(int amount) {

        int temp = account;
        if (temp + amount <= 0) {

            return account += amount;
        } else {
            throw new EnlargeException("Операция отклонена, баланс кредитной карты не может быть больше 0!");
        }
    }

    @Override
    protected int reduce(int amount) {
        return account -= amount;
    }
}