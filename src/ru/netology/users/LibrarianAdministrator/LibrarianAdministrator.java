package ru.netology.users.LibrarianAdministrator;

import ru.netology.users.Administrator;
import ru.netology.users.Librarian;
import ru.netology.users.Reader;
import ru.netology.users.Supplier;

public class LibrarianAdministrator implements Librarian, Administrator {

    private String name;

    public LibrarianAdministrator(String name) {
        this.name = name;
    }

    @Override
    public void overdueNotification(Reader reader, String book) {
        System.out.println(name + ": -Нужно вернуть Книгу " + book);
        reader.returnBook(book);
    }

    @Override
    public boolean findBook(String book) {

        if (!book.equals("Книга")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void giveBook(String book, Reader reader) {

        if (findBook(book)) {
            System.out.println(name + ": -Держи книгу " + book + " дружище");
        } else {
            System.out.println(name + ": -Что то не нашел я " + book);
        }

    }

    @Override
    public void orderBook(String book, Supplier supplier) {

        System.out.println(name + ": -Нужно заказать книгу " + book + " у поставщика");
        supplier.bringBook(book);
    }
}
