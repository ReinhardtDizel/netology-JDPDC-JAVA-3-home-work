package ru.netology;

import ru.netology.accounts.AccountImpl.AccountImpl;
import ru.netology.accounts.CheckingAccount.CheckingAccount;
import ru.netology.accounts.CreditAccount.CreditAccount;
import ru.netology.accounts.SavingsAccount.SavingsAccount;

public class Main {

    public static void main(String[] args) {

        AccountImpl checkingAccount1 = new CheckingAccount(0);
        AccountImpl creditAccount1 = new CreditAccount(-10_000);
        AccountImpl savingsAccount1 = new SavingsAccount(10_000);
        AccountImpl savingsAccount2 = new SavingsAccount(15_000);

        System.out.printf("\nОперации со счетом %s: \n", checkingAccount1.getClass().getSimpleName());
        checkingAccount1.pay(100);// На хлебушек
        checkingAccount1.addMoney(1000);
        checkingAccount1.pay(100);
        checkingAccount1.transfer(creditAccount1, 900);

        System.out.printf("\nОперации со счетом %s: \n", creditAccount1.getClass().getSimpleName());
        creditAccount1.printBalance();
        creditAccount1.pay(5000);
        creditAccount1.addMoney(15000);
        creditAccount1.addMoney(14000);

        System.out.printf("\nОперации со счетом %s: \n", savingsAccount1.getClass().getSimpleName());
        savingsAccount1.pay(200);
        savingsAccount1.printBalance();
        savingsAccount1.transfer(creditAccount1, 1000);
        savingsAccount1.transfer(savingsAccount2, 1000);
        savingsAccount1.addMoney(10_000);

    }
}