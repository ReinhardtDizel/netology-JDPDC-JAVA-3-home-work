package ru.netology.Model;

import java.util.ArrayList;

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

    public String toString() {

        return "ISBN : " + isbn + '\n' +
                "Title: " + title + '\n' +
                "Authors: " + authorsToString() + '\n' +
                "Number of Pages: " + pagesCount + '\n';
    }
}
