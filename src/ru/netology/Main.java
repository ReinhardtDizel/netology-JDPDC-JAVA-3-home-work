package ru.netology;


import ru.netology.users.LibrarianAdministrator.LibrarianAdministrator;
import ru.netology.users.ReaderImpl.ReaderImpl;
import ru.netology.users.SupplierReader.SupplierReader;

class Main {

    public static void main(String[] args) {

        String bookOne = "Книга";
        String javaBook = "Очень Популярная Книга по Java";
        String bookTwo = "Война и Мир";
        String oldBook = "Очень Старая Книга";

        LibrarianAdministrator librarianAdministrator = new LibrarianAdministrator("Костян");
        SupplierReader supplierReader = new SupplierReader("Петруха");
        ReaderImpl reader = new ReaderImpl("Васёк");

        reader.takeBook(oldBook, librarianAdministrator);
        librarianAdministrator.orderBook(javaBook, supplierReader);
        supplierReader.takeBook(bookTwo, librarianAdministrator);
        librarianAdministrator.overdueNotification(reader, oldBook);

    }
}