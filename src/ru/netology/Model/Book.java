package ru.netology.Model;

import java.util.ArrayList;
import java.util.Objects;

public class Book {

    private String isbn;
    private String title;
    private ArrayList<Author> authors = new ArrayList<>();
    private int pagesCount;

    public Book(String isbn, String title, Author author, int pagesCount) {
        this.isbn = isbn;
        this.title = title;
        this.authors.add(author);
        this.pagesCount = pagesCount;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    private String authorsToString() {
        StringBuffer bf = new StringBuffer();
        int count = 0;
        for (Author author : authors) {
            bf.append(author.getFirstName());
            bf.append(" ");
            bf.append(author.getLastName());
            if (count == authors.size() - 1) {
                bf.append(".");
            } else {
                bf.append(", ");
            }
            ++count;
        }
        return bf.toString();
    }

    public boolean equals(Book book) {

        return isbn.equals(book.getIsbn()) &&
                title.equals(book.getTitle()) &&
                pagesCount == book.getPagesCount() &&
                authors.equals(book.getAuthors());
    }

    public String toString() {
        final String AUTHOR = "Автор: ";
        final String AUTHORS = "Авторы: ";
        String str = authors.size() > 1 ? AUTHORS : AUTHOR;

        return "ISBN : " + isbn + '\n' +
                "Название: " + title + '\n' +
                str + authorsToString() + '\n' +
                "Количество страниц: " + pagesCount + '\n';
    }
}
