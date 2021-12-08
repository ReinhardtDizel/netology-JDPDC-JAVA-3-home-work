package ru.netology;

import ru.netology.exceptions.AccessDeniedException;
import ru.netology.exceptions.UserNotFoundException;

import java.util.Scanner;

public class Main {

    public static User[] getUsers() {
        User user1 = new User("jhon", "jhon@gmail.com", "pass", 24);
        User user2 = new User("mike", "mike@gmail.com", "pass", 15);
        User user3 = new User("tom", "tom@gmail.com", "pass", 50);
        User user4 = new User("jerry", "jerry@gmail.com", "pass", 70);
        return new User[]{user1, user2, user3, user4};
    }

    public static User getUserByLoginAndPassword(String login, String password) throws UserNotFoundException {
        User[] users = getUsers();
        for (User user : users) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        throw new UserNotFoundException("User not found");
    }

    public static void validateUser(User user) throws AccessDeniedException {
        final int VALID_AGE = 18;
        if (user.getAge() < VALID_AGE) {
            throw new AccessDeniedException(String.format("Access Denied. Age is %d\n", user.getAge()));
        }
    }

    public static void main(String[] args) throws UserNotFoundException, AccessDeniedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите логин");
        String login = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();

        try {
            User user = getUserByLoginAndPassword(login, password);
            validateUser(user);
        } catch (UserNotFoundException | AccessDeniedException exception) {
            throw exception;
        }
        System.out.println("Доступ предоставлен");
    }
}