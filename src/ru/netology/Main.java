package ru.netology;

import java.util.*;

class Main {

    final static String STR_PROGRAM_GREETINGS_MESSAGE = "Здравствуйте.";
    final static String STR_PROGRAM_GREETINGS_MORNING_MESSAGE = "Доброе утро!";
    final static String STR_PROGRAM_GREETINGS_DAY_MESSAGE = "Добрый день!";
    final static String STR_PROGRAM_GREETINGS_EVENING_MESSAGE = "Добрый вечер!";
    final static String STR_PROGRAM_GREETINGS_NIGHT_MESSAGE = "Спать уже давно пора!:)";
    final static String STR_SELECT_OPERATION_MESSAGE = "Выберите операцию и введите её номер:";
    final static String STR_NO_OPERATION_MESSAGE = "Такой операции нет!";
    final static String STR_NUMBER_FORMAT_EXCEPTION_MESSAGE = "Введите цифры или end для завершения рботы программы!";
    final static String STR_ENTER_EARNINGS_MESSAGE = "Введите сумму дохода:";
    final static String STR_ENTER_SPENDING_MESSAGE = "Введите сумму расхода:";
    final static String STR_EARNING_OPERATION_MENU = "1. Добавить новый доход";
    final static String STR_SPENDING_OPERATION_MENU = "2. Добавить новый расход";
    final static String STR_TAX_TIP_OPERATION_MENU = "3. Выбрать систему налогооблажения";
    final static String STR_TAX_SYSTEM_ONE_RU = "УСН доходы";
    final static String STR_TAX_SYSTEM_TWO_RU = "УСН доходы минус расходы";
    final static String STR_PROGRAM_END = "end";
    final static String STR_PROGRAM_END_MESSAGE = "Программа завершена";
    final static char WHITESPACE_CHAR = '\u00A0';
    static String resultMessage =
            "Мы советуем вам %s\n" +
                    "Ваш налог составит: %s рублей\n" +
                    "Налог на другой системе: %s рублей\n" +
                    "Экономия: %s рублей\n";

    public static int getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    final static int rightNow = getTime();

    final static int TAX_RATE_EARNINGS_MINUS_SPENDING = 15;
    final static int TAX_RATE_EARNINGS_ONLY = 6;
    static int earnings = 0; // доходы
    static int spending = 0; // расходы
    static int taxEarningsMinusSpending = 0; // налог УСН доходы минус расходы
    static int taxEarningsOnly = 0; // налог УСН доходы
    static int saving = 0; // экономия
    static int bestTaxValue = 0; // налог по самой выгодной системе
    static int otherTaxValue = 0; // налог по другой системе

    static String bestTaxSystem = "";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        setColorSchema();
        showGreetings();
        programLifeCircle:
        while (true) {
            showMenu();
            String input = scanner.nextLine();
            if (STR_PROGRAM_END.equals(input)) {
                break;
            }
            int operation = checkingMenuSelection(input);
            switch (operation) {
                case 1:
                    if (!setEarnings(scanner)) {
                        break programLifeCircle;
                    }
                    break;
                case 2:
                    if (!setSpending(scanner)) {
                        break programLifeCircle;
                    }
                    break;
                case 3:
                    computeResult();
                    showResult();
                    break;
                case 0:
                    break;
                default:
                    showMenuException();
                    break;
            }
        }
        programEnd();
    }

    public static void setColorSchema() {
        bannerColor = ANSI_GREEN;
        menuColor = ANSI_CYAN;
        errorColor = ANSI_YELLOW;
        endColor = ANSI_RED;
        resultColor = ANSI_GREEN;
    }

    public static void showGreetings() {
        System.out.println(bannerColor + BANNER_STARTER);
        System.out.println(generatedGreetingMessage() + ANSI_RESET);
        System.out.println();
    }

    public static String generatedGreetingMessage() {
        if (rightNow >= 4 && rightNow <= 11) {
            return STR_PROGRAM_GREETINGS_MORNING_MESSAGE;
        } else if (rightNow >= 12 && rightNow <= 18) {
            return STR_PROGRAM_GREETINGS_DAY_MESSAGE;
        } else if (rightNow >= 19 && rightNow <= 24) {
            return STR_PROGRAM_GREETINGS_EVENING_MESSAGE;
        } else {
            return STR_PROGRAM_GREETINGS_MESSAGE + WHITESPACE_CHAR + STR_PROGRAM_GREETINGS_NIGHT_MESSAGE;
        }
    }

    public static void showMenu() {
        System.out.println(menuColor + STR_SELECT_OPERATION_MESSAGE + ANSI_RESET);
        System.out.println(STR_EARNING_OPERATION_MENU);
        System.out.println(STR_SPENDING_OPERATION_MENU);
        System.out.println(STR_TAX_TIP_OPERATION_MENU);
    }

    public static void showMenuException() {
        System.out.println(errorColor + STR_NO_OPERATION_MESSAGE + ANSI_RESET);
    }

    public static int checkingMenuSelection(String input) throws NumberFormatException {
        int operation = 0;
        try {
            operation = Integer.parseInt(input);
            return operation;
        } catch (NumberFormatException e) {
            System.out.println(errorColor + STR_NUMBER_FORMAT_EXCEPTION_MESSAGE + ANSI_RESET);
        }
        return operation;
    }

    public static boolean setEarnings(Scanner scanner) throws NumberFormatException {
        System.out.println(menuColor + STR_ENTER_EARNINGS_MESSAGE + ANSI_RESET);
        String input = scanner.nextLine();
        try {
            final int parseInput = Integer.parseInt(input);
            setEarnings(parseInput);
            return true;
        } catch (NumberFormatException e) {
            if (input.equals(STR_PROGRAM_END)) {
                return false;
            } else {
                System.out.println(errorColor + STR_NUMBER_FORMAT_EXCEPTION_MESSAGE);
                return true;
            }
        }
    }

    public static boolean setSpending(Scanner scanner) throws NumberFormatException {
        System.out.println(menuColor + STR_ENTER_SPENDING_MESSAGE + ANSI_RESET);
        String input = scanner.nextLine();
        try {
            final int parseInput = Integer.parseInt(input);
            setSpending(parseInput);
            return true;
        } catch (NumberFormatException e) {
            if (input.equals(STR_PROGRAM_END)) {
                return false;
            } else {
                System.out.println(errorColor + STR_NUMBER_FORMAT_EXCEPTION_MESSAGE);
                return true;
            }
        }

    }

    public static void computeResult() {
        taxEarningsMinusSpending();
        taxEarningsOnly();
        setBestTaxSystem();
        setSaving();
    }

    public static void showResult() {
        System.out.printf((resultMessage) + "%n",
                getBestTaxSystem(),
                getBestTaxValue(),
                getOtherTaxValue(),
                getSaving()
        );
        System.out.println();
    }

    public static void programEnd() {
        System.out.println();
        System.out.println(endColor + STR_PROGRAM_END_MESSAGE + ANSI_RESET);
    }

    public static void taxEarningsMinusSpending() {
        int tax = (getEarnings() - getSpending()) * TAX_RATE_EARNINGS_MINUS_SPENDING / 100;
        setTaxEarningsMinusSpending(Math.max(tax, 0));
    }

    public static void taxEarningsOnly() {
        int tax = getEarnings() * TAX_RATE_EARNINGS_ONLY / 100;
        setTaxEarningsOnly(Math.max(tax, 0));
    }

    public static void setBestTaxSystem() {
        final int taxEarningsMinusSpending = getTaxEarningsMinusSpending();
        final int taxEarningsOnly = getTaxEarningsOnly();
        setBestTaxSystem(taxEarningsOnly < taxEarningsMinusSpending ? STR_TAX_SYSTEM_ONE_RU : STR_TAX_SYSTEM_TWO_RU);
        setBestTaxValue(Math.min(taxEarningsOnly, taxEarningsMinusSpending));
        setOtherTaxValue(Math.max(taxEarningsOnly, taxEarningsMinusSpending));
    }

    public static void setSaving() {
        final int taxEarningsMinusSpending = getTaxEarningsMinusSpending();
        final int taxEarningsOnly = getTaxEarningsOnly();
        setSaving(Math.abs(taxEarningsOnly - taxEarningsMinusSpending));
    }

    public static int getEarnings() {
        return earnings;
    }

    public static int getSpending() {
        return spending;
    }

    public static void setEarnings(final int earning) {
        earnings += earning;
    }

    public static void setSpending(final int spe) {
        spending += spe;
    }

    public static int getTaxEarningsOnly() {
        return taxEarningsOnly;
    }

    public static void setTaxEarningsOnly(final int tax) {
        taxEarningsOnly = tax;
    }

    public static int getTaxEarningsMinusSpending() {
        return taxEarningsMinusSpending;
    }

    public static void setTaxEarningsMinusSpending(final int tax) {
        taxEarningsMinusSpending = tax;
    }

    public static String getSaving() {
        return resultColor + saving + ANSI_RESET;
    }

    public static void setSaving(final int tax) {
        saving = tax;
    }

    public static String getBestTaxValue() {
        return resultColor + bestTaxValue + ANSI_RESET;
    }

    public static void setBestTaxValue(final int bestTaxValue) {
        Main.bestTaxValue = bestTaxValue;
    }

    public static String getOtherTaxValue() {
        return resultColor + otherTaxValue + ANSI_RESET;
    }

    public static void setOtherTaxValue(final int tax) {
        otherTaxValue = tax;
    }

    public static String getBestTaxSystem() {
        return resultColor + bestTaxSystem + ANSI_RESET;
    }

    public static void setBestTaxSystem(final String system) {
        bestTaxSystem = system;
    }


    // Цвета для текста
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    // Поля для изменения цвета
    public static String bannerColor = "";
    public static String menuColor = "";
    public static String errorColor = "";
    public static String endColor = "";
    public static String resultColor = "";

    /**
     * <P>Это баннер
     * <P><code>JDPDC-JAVA-3</code>
     */
    final static String BANNER_STARTER =
    " ╦╔╦╗╔═╗╔╦╗╔═╗        ╦╔═╗╦  ╦╔═╗       ╦  ╦  ╦\n" +
    " ║ ║║╠═╝ ║║║    ───   ║╠═╣╚╗╔╝╠═╣  ───  ║  ║  ║\n" +
    "╚╝═╩╝╩  ═╩╝╚═╝       ╚╝╩ ╩ ╚╝ ╩ ╩       ╩  ╩  ╩";
}
