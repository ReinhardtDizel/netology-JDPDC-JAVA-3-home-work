package ru.netology.Model;

public class User {

    private static int totalOnline;
    private String firstName;
    private String lastName;
    private String email;

    public User(String firstName, String lastName, String email) {
        ++totalOnline;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email.isEmpty() ? NO_DATA : email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public static int getTotalOnline() {
        return totalOnline;
    }

    public boolean equals(User user) {
        return firstName.equals(user.getFirstName()) && lastName.equals(user.getLastName()) && email.equals(user.getEmail());
    }

    public String toString() {

        return firstName + " " + lastName + ", email: " + email + '\n';
    }

    private static final String NO_DATA = "нет данных";
}
