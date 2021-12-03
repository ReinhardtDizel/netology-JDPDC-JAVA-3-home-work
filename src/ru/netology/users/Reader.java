package ru.netology.users;

public interface Reader {

    void takeBook(String book, Administrator administrator);

    void returnBook(String book);
}
