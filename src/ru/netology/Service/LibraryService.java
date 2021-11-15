package ru.netology.Service;

import ru.netology.Model.Book;
import ru.netology.Model.User;

public class LibraryService {

    private  BookService bookService;
    private  UserService userService;

    public LibraryService() {
        bookService = new BookService();
        userService = new UserService();
    }

    public void run() {

        usersView();
        booksView();
    }

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public void addUser(User user){
        userService.addUser(user);
    }

    public void booksView() {
        bookService.printAll();
    }

    public void usersView() {
        userService.printAll();
    }
}

