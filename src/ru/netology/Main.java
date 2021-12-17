package ru.netology;

import ru.netology.events.Event;
import ru.netology.events.Movie;
import ru.netology.events.Theatre;

public class Main {

    public static Movie[] getMovies() {
        return new Movie[]{
                new Movie("Начало", 2010, 16),
                new Movie("Конец", 2020, 18),
                new Movie("", 2020, 18),

        };
    }

    public static Theatre[] getTheatres() {
        return new Theatre[]{
                new Theatre("Анна Каренина", 2017, 16),
                new Theatre("Анна Петровна", 2010, 10),
                new Theatre("Монти Пайтон и Священный Грааль", 1975, -1)
        };
    }


    public static void validEvent(Event event) {

        if (event.getTitle().isEmpty() || event.getTitle() == null || event.getReleaseYear() == 0 || event.getAge() == 0) {
            throw new RuntimeException(String.format("В событии должны быть заполнены все поля! %s: %s\n", event.getClass().getSimpleName(), event));
        }
    }

    public static void main(String[] args) {

        for (Event event : getMovies()) {
            validEvent(event);
        }
        for (Event event : getTheatres()) {
            validEvent(event);
        }
        System.out.println("Все события корректны");
    }
}