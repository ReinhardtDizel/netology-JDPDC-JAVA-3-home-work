package ru.netology;

import ru.netology.Notebook.Notebook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Notebook myNotebook = new Notebook();

    private static int showMenu() {

        int input = -1;
        System.out.println("Выберите действие:\n1. Добавить задачу\n2. Вывести список задач\n3. Удалить задачу\n0. Выход");
        try {

            input = scanner.nextInt();
            if (input > 3 || input < 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException exception) {
            System.out.println("Введите валидное число");
        }
        return input;

    }

    private static void addTask() {

        String task;
        System.out.println("Введите задачу для планирования:");
        task = readInput();
        myNotebook.newTask(task);
    }

    private static void printAll() {

        System.out.println("Список задач:");
        System.out.println(myNotebook);
    }

    private static void deleteTask() {

        String task;
        int taskNum;
        printAll();
        System.out.println("Введите задачу для удаления:");
        task = readInput();
        try {
            taskNum = Integer.parseInt(task);
            myNotebook.deleteTask(taskNum);
        } catch (NumberFormatException e) {
            myNotebook.deleteTask(task);
        }
    }

    private static String readInput() {
        
        String input = "";
        while (input.isEmpty()) {
            input = scanner.nextLine();
        }
        return input;
    }

    public static void main(String... args) {

        int input = -1;

        while (input != 0) {

            input = showMenu();

            switch (input) {
                case 1:
                    addTask();
                    break;
                case 2:
                    printAll();
                    break;
                case 3:
                    deleteTask();
                    break;
            }

        }

    }
}
