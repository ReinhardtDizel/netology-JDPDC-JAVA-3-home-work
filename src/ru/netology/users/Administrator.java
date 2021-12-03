package ru.netology.users;

public interface Administrator {

    void overdueNotification(Reader reader, String book);

    boolean findBook(String book);

    void giveBook(String book, Reader reader);
}
