package ru.netology.accounts;

import ru.netology.accounts.AccountImpl.AccountImpl;

public interface Account {

    void pay(int amount);

    void transfer(AccountImpl account, int amount);

    void addMoney(int amount);

    void printBalance();

    int getBalance();
}
