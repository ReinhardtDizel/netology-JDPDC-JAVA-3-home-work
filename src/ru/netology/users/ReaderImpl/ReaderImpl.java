package ru.netology.users.ReaderImpl;

import ru.netology.users.Administrator;
import ru.netology.users.Reader;

public class ReaderImpl implements Reader {

    private String name;

    public ReaderImpl(String name) {
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
}
