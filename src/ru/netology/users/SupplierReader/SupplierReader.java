package ru.netology.users.SupplierReader;

import ru.netology.users.Administrator;
import ru.netology.users.Reader;
import ru.netology.users.Supplier;

public class SupplierReader implements Reader, Supplier {

    private String name;

    public SupplierReader(String name) {
        this.name = name;
    }

    @Override
    public void takeBook(String book, Administrator administrator) {

        System.out.println(name + ": -Хочу книгу почитать: " + book);
        administrator.giveBook(book,this);
    }

    @Override
    public void returnBook(String book) {

        System.out.println(name + ": -Возвращаю книгу: " + book);
    }

    @Override
    public void bringBook(String book) {

        System.out.println(name + ": -Скоро привезу тебе книгу " + book);
    }
}
