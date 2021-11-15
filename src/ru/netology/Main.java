package ru.netology;


import ru.netology.Model.Author;
import ru.netology.Model.Book;
import ru.netology.Model.User;
import ru.netology.Service.LibraryService;

class Main {

    static String totalUsersOnline = "Онлайн %d пользователей";

    public static void main (String[] args) throws Exception {

        User user4 = new User("Джонс", "Терри", "flying@mail.ru");
        User user5 = new User("Джон", "Клиз", "circus@mail.ru");
        User user6 = new User("Чэпмен", "Грэм", "");

        LibraryService libraryService = new LibraryService();

        libraryService.addUser(user4);
        libraryService.addUser(user5);
        libraryService.addUser(user6);

        libraryService.addUser(user6);

        Author author = new Author("Шилдт", "Герберт");

        Book book = new Book(
                "978-5-6040043-6-4",
                "Java. Полное руководство",
                author,
                1488);

        Book bookTest = new Book(
                "978-5-6040043-6-4",
                "Java. Полное руководство",
                author,
                1488);

        System.out.println(book.equals(bookTest) ? "Это книга такая же!" : "Это другая книга");

        System.out.println(ANSI_YELLOW + "=====================================================" + ANSI_RESET);
        System.out.println(String.format(totalUsersOnline, user6.getTotalOnline()));
        System.out.println(ANSI_YELLOW + "=====================================================" + ANSI_RESET);

        libraryService.run();
        System.out.println(ANSI_RED + "=====================================================" + ANSI_RESET);
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
}
