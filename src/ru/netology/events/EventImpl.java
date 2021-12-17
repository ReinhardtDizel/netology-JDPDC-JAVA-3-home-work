package ru.netology.events;

public abstract class EventImpl implements Event{

    private String title;
    private int releaseYear;
    private int age;

    public EventImpl(String title, int releaseYear, int age) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.age = age;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {

        return String.format("%s %d год. только для лиц старше %d", getTitle(), getReleaseYear(), getAge());
    }
}
