package ru.netology.Service;

import ru.netology.Model.User;
import ru.netology.dao.UserDao;

import java.util.ArrayList;

public class UserService {

    private ArrayList<User> users;
    private UserDao userDao = new UserDao();

    public UserService() {
        this.users = userDao.getData();

    }

    public void addUser(User user) {
        this.userDao.save(user);
    }

    public void printAll() {
        for (User user : users) {

            System.out.println(user);
        }
    }
}
