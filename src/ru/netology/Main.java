package ru.netology;

import java.util.*;

class Main {

    final static String STR_PROGRAM_END = "end";

    final static String STR_PROGRAM_END_MESSAGE = "Программа завершена!";
    final static String STR_PROGRAM_GREETINGS_MESSAGE = "Здравствуйте!";
    final static String STR_SELECT_OPERATION_MESSAGE = "Выберите операцию и введите её номер:";
    final static String STR_NO_OPERATION_MESSAGE = "Такой операции нет!";
    final static String STR_ENTER_EARNINGS_MESSAGE = "Введите сумму дохода:";
    final static String STR_ENTER_SPENDING_MESSAGE = "Введите сумму расхода:";

    final static String STR_EARNING_OPERATION = "Добавить новый доход";
    final static String STR_SPENDING_OPERATION = "Добавить новый расход";
    final static String STR_TAX_TIP_OPERATION = "Выбрать систему налогооблажения";

    final static String STR_PAYMENT_SYSTEM = "рублей";

    final static String STR_SAVINGS_RESULT = "Экономия";
    final static String STR_TAX_OTHER_SYSTEM_RESULT = "Налог на другой системе:";
    final static String STR_TAX_SYSTEM_TIP_RESULT = "Мы советуем Вам выбрать:";
    final static String STR_TAX_RESULT_RESULT = "Ваш налог составит:";

    final static String STR_TAX_SYSTEM_ONE_RU = "УСН доходы";
    final static String STR_TAX_SYSTEM_TWO_RU = "УСН доходы минус расходы";
    final static char WHITESPACE_CHAR = '\u00A0';

    final static int TAX_RATE_EARNINGS_MINUS_SPENDING = 15;
    final static int TAX_RATE_EARNINGS_ONLY = 6;

    static int earnings = 0; // доходы
    static int spending = 0; // расходы

    static int taxEarningsMinusSpending = 0;
    static int taxEarningsOnly = 0;

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
            int operation = Integer.parseInt(input);
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

    public static void showGreetings() {
        System.out.println();
        System.out.println(STR_PROGRAM_GREETINGS_MESSAGE);
        System.out.println();
    }

    public static void showMenu() {
        System.out.println(STR_SELECT_OPERATION_MESSAGE);
        System.out.println("1." + WHITESPACE_CHAR + STR_EARNING_OPERATION);
        System.out.println("2." + WHITESPACE_CHAR + STR_SPENDING_OPERATION);
        System.out.println("3." + WHITESPACE_CHAR + STR_TAX_TIP_OPERATION);
    }

    public static void computeResult() {
        taxEarningsMinusSpending();
        taxEarningsOnly();
        setBestTaxSystem();
        setSaving();
    }

    public static void showResult() {
        System.out.println(STR_TAX_SYSTEM_TIP_RESULT + WHITESPACE_CHAR + getBestTaxSystem());
        System.out.println(STR_TAX_RESULT_RESULT + WHITESPACE_CHAR + getBestTaxValue() + WHITESPACE_CHAR + STR_PAYMENT_SYSTEM);
        System.out.println(STR_TAX_OTHER_SYSTEM_RESULT + WHITESPACE_CHAR + getOtherTaxValue() + WHITESPACE_CHAR + STR_PAYMENT_SYSTEM);
        System.out.println(STR_SAVINGS_RESULT + WHITESPACE_CHAR + getSaving() + WHITESPACE_CHAR + STR_PAYMENT_SYSTEM);
        System.out.println();
    }

    public static void setEarnings(Scanner scanner) {
        System.out.println(STR_ENTER_EARNINGS_MESSAGE);
        final int input = Integer.parseInt((scanner.nextLine()));
        setEarnings(input);
    }

    public static void setSpending(Scanner scanner) {
        System.out.println(STR_ENTER_SPENDING_MESSAGE);
        final int input = Integer.parseInt((scanner.nextLine()));
        setSpending(input);
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
}
