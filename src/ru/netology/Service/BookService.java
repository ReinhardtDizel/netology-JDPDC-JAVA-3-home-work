package ru.netology.Service;

import ru.netology.Model.Book;
import ru.netology.dao.BookDao;

import java.util.ArrayList;

public class BookService {

    private ArrayList<Book> books;
    private BookDao bookDao = new BookDao();

    public BookService() {
        this.books = bookDao.getData();
    }

    public void addBook(Book book) {
        bookDao.save(book);
    }

    public void printAll() {
        for (Book book : books) {

            System.out.println(book);
        }
    }
}

