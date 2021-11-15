package ru.netology.dao;

import ru.netology.Model.Author;
import ru.netology.Model.Book;

import java.util.ArrayList;

public class BookDao {

    private ArrayList<Book> booksDao = new ArrayList<>();

    public  BookDao() {

        Author author1 = new Author("Шилдт", "Герберт");
        Author author2 = new Author("Хорстманн", "Кей");
        Author author3 = new Author("Фримен", "Эрик");
        Author author4 = new Author("Робсон", "Элизабет");

        Book book1 = new Book(
                "978-5-6040043-6-4",
                "Java. Полное руководство",
                author1,
                1488);
        Book book2 = new Book(
                "978-5-907144-38-5",
                "Java. Библиотека профессионала. Том 2. Расширенные средства программирования",
                author2,
                864);
        Book book3 = new Book(
                "978-5-4461-0893-0",
                "Изучаем программирование на JavaScript",
                author3,
                640);
        book3.addAuthor(author4);

        booksDao.add(book1);
        booksDao.add(book2);
        booksDao.add(book3);
    }

    public  ArrayList<Book> getData() {
        return booksDao;
    }

    public Book getById(final int id) {
        try {
            return booksDao.get(id);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public boolean bookIsExist(Book book) {
        boolean exist = false;
        for (Book b : booksDao) {
           exist = b.equals(book);
        }
        return exist;
    }

    public void save(Book book) {
        if (!bookIsExist(book)) {
            this.booksDao.add(book);
        } else {
            System.out.println("такая книга уже есть!");
        }
    }
}
