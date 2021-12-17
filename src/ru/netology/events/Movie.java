package ru.netology.events;

public class Movie extends EventImpl {
    public Movie(String title, int releaseYear, int age) {
        super(title, releaseYear, age);
    }
}
