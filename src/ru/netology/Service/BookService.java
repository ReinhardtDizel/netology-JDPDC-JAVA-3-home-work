package ru.netology.Service;

import ru.netology.Model.Author;
import ru.netology.Model.Book;

import java.util.ArrayList;

public class BookService {

    private static ArrayList<Book> books = new ArrayList<>();

    private static void initBookService() {

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

        books.add(book1);
        books.add(book2);
        books.add(book3);
    }

    public static void run() {

        initBookService();
        for (Book book : books) {

            System.out.println(book);
        }
    }
}
