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
    final static String STR_NUMBER_FORMAT_EXCEPTION_MESSAGE = "Введите цифры!";
    final static String STR_PROGRAM_END_HELP_MESSAGE = "Чтобы выйти, введите end, находясь в меню";
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
            "Ваш налог составит: %d рублей\n" +
            "Налог на другой системе: %d рублей\n" +
            "Экономия: %d рублей\n";

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
        showGreetings();
        while (true) {
            showMenu();
            String input = scanner.nextLine();
            if (STR_PROGRAM_END.equals(input)) {
                break;
            }
            int operation = checkingMenuSelection(input);
            switch (operation) {
                case 1:
                    setEarnings(scanner);
                    break;
                case 2:
                    setSpending(scanner);
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

    public static void programEnd() {
        System.out.println();
        System.out.println(STR_PROGRAM_END_MESSAGE);
    }

    public static void showMenuException() {
        System.out.println(STR_NO_OPERATION_MESSAGE);
    }

    public static int checkingMenuSelection(String input) throws NumberFormatException{
        int operation = 0;
        try {
            operation = Integer.parseInt(input);
            return operation;
        } catch (NumberFormatException e) {
            System.out.println(STR_NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
        return operation;
    }

    public static void showGreetings() {
        System.out.println(BANNER_STARTER);
        System.out.println(generatedGreetingMessage());
        System.out.println();
    }

    public static void showMenu() {
        System.out.println(STR_SELECT_OPERATION_MESSAGE);
        System.out.println(STR_EARNING_OPERATION_MENU);
        System.out.println(STR_SPENDING_OPERATION_MENU);
        System.out.println(STR_TAX_TIP_OPERATION_MENU);
    }

    public static void computeResult() {
        taxEarningsMinusSpending();
        taxEarningsOnly();
        setBestTaxSystem();
        setSaving();
    }

    public static void showResult() {
        System.out.println(String
                .format(resultMessage,
                        getBestTaxSystem(),
                        getBestTaxValue(),
                        getOtherTaxValue(),
                        getSaving()
                ));
        System.out.println();
    }

    public static void setEarnings(Scanner scanner)  throws NumberFormatException {
        System.out.println(STR_ENTER_EARNINGS_MESSAGE);
        String input = scanner.nextLine();
        try {
            final int parseInput = Integer.parseInt(input);
            setEarnings(parseInput);
        } catch (NumberFormatException e) {
            if (input.equals(STR_PROGRAM_END)) {
                System.out.println(STR_PROGRAM_END_HELP_MESSAGE);
            } else {
                System.out.println(STR_NUMBER_FORMAT_EXCEPTION_MESSAGE);
            }
        }
    }

    public static void setSpending(Scanner scanner) throws NumberFormatException {
        System.out.println(STR_ENTER_SPENDING_MESSAGE);
        String input = scanner.nextLine();
        try {
            final int parseInput = Integer.parseInt(input);
            setSpending(parseInput);
        } catch (NumberFormatException e) {
            if (input.equals(STR_PROGRAM_END)) {
                System.out.println(STR_PROGRAM_END_HELP_MESSAGE);
            } else {
                System.out.println(STR_NUMBER_FORMAT_EXCEPTION_MESSAGE);
            }
        }

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

    public static int getSaving() {
        return saving;
    }

    public static void setSaving(final int tax) {
        saving = tax;
    }

    public static int getBestTaxValue() {
        return bestTaxValue;
    }

    public static void setBestTaxValue(final int bestTaxValue) {
        Main.bestTaxValue = bestTaxValue;
    }

    public static int getOtherTaxValue() {
        return otherTaxValue;
    }

    public static void setOtherTaxValue(final int tax) {
        otherTaxValue = tax;
    }

    public static String getBestTaxSystem() {
        return bestTaxSystem;
    }

    public static void setBestTaxSystem(final String system) {
        bestTaxSystem = system;
    }

    final static String BANNER_STARTER =
    " ╦╔╦╗╔═╗╔╦╗╔═╗        ╦╔═╗╦  ╦╔═╗       ╦  ╦  ╦\n" +
    " ║ ║║╠═╝ ║║║    ───   ║╠═╣╚╗╔╝╠═╣  ───  ║  ║  ║\n" +
    "╚╝═╩╝╩  ═╩╝╚═╝       ╚╝╩ ╩ ╚╝ ╩ ╩       ╩  ╩  ╩";
}
