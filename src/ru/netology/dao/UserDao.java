package ru.netology.dao;

import ru.netology.Model.Book;
import ru.netology.Model.User;

import java.util.ArrayList;

public class UserDao {

    private ArrayList<User> usersDao = new ArrayList<>();

    public UserDao() {

        User user1 = new User("Иван", "Данко", "redheat@mail.ru");
        User user2 = new User("Айдл", "Эрик", "monty@mail.ru");
        User user3 = new User("Гиллиам", "Терри", "python@mail.ru");
        usersDao.add(user1);
        usersDao.add(user2);
        usersDao.add(user3);
    }

    public ArrayList<User> getData() {
        return usersDao;
    }

    public User getById(final int id) {
        try {
            return usersDao.get(id);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public boolean userIsExist(User user) {
        boolean exist = false;
        for (User u : usersDao) {
            exist = u.equals(user);
        }
        return exist;
    }

    public void save(User user) {
        if (!userIsExist(user)) {
            this.usersDao.add(user);
        } else {
            System.out.println("такой пользователь уже существует!");
        }
    }
}
