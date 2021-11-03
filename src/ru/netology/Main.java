package ru.netology;

import java.util.*;

class Main {

    final static String STR_PROGRAM_END = "end";
    final static String STR_PROGRAM_END_MESSAGE = "Программа завершена!";
    final static String STR_PROGRAM_GREETINGS_MESSAGE = "Здравствуйте!";
    final static String STR_SELECT_OPERATION_MESSAGE = "Выберите операцию и введите её номер:";
    final static String STR_ENTER_EARNINGS = "Введите сумму дохода:";
    final static String STR_ENTER_SPENDING = "Введите сумму расхода:";
    final static String STR_NO_OPERATION_MESSAGE = "Такой операции нет!";
    final static String STR_NO_TAX_SYSTEM_MESSAGE = "Такой системы налогооблажения нет!";
    final static String STR_TAX_SYSTEM_TIP_MESSAGE = "Мы советуем Вам выбрать:";
    final static String STR_EARNING_OPERATION = "Добавить новый доход";
    final static String STR_SPENDING_OPERATION = "Добавить новый расход";
    final static String STR_TAX_TIP_OPERATION = "Выбрать систему налогооблажения";
    final static String STR_TAX_RESULT = "Ваш налог составит:";
    final static String STR_PAYMENT_SYSTEM = "рублей";
    final static String STR_TAX_OTHER_SYSTEM_RESULT = "Налог на другой системе:";
    final static String STR_SAVINGS_RESULT = "Экономия";
    final static String STR_TAX_SYSTEM_ONE_RU = "УСН доходы";
    final static String STR_TAX_SYSTEM_TWO_RU = "УСН доходы минус расходы";

    static int earnings = 0;
    static int spending = 0;
    static int saving = 0;
    static int taxEarningsOnly = 0;
    static int taxEarningsMinusSpending = 0;

    static String bestTaxSystem = "";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = new String();
        System.out.println(STR_PROGRAM_GREETINGS_MESSAGE);
        while (true) {
            System.out.println(STR_SELECT_OPERATION_MESSAGE);
            System.out.println("1. " + STR_EARNING_OPERATION);
            System.out.println("2. " + STR_SPENDING_OPERATION);
            System.out.println("3. " + STR_TAX_TIP_OPERATION);
            input = scanner.nextLine();
            if (STR_PROGRAM_END.equals(input)) {
                break;
            }
            switch (input) {
                case "1":
                    setEarnings(scanner);
                    break;
                case "2":
                    setSpending(scanner);
                    break;
                case "3":
                    setBestTaxSystem();
                    setSaving();
                    showResult();
                    break;
                default:
                    System.out.println(STR_NO_OPERATION_MESSAGE);
                    break;
            }
        }
        System.out.println(STR_PROGRAM_END_MESSAGE);
    }

    /**
     * Мы советуем вам УСН доходы
     * Ваш налог составит: 60 рублей
     * Налог на другой системе: 135 рублей
     * Экономия: 75 рублей
     */
    public static void showResult() {
        System.out.println(STR_TAX_SYSTEM_TIP_MESSAGE + '\t' + getBestTaxSystem());
        System.out.println(STR_TAX_RESULT + '\t' + getTaxEarningsOnly() + '\t' + STR_PAYMENT_SYSTEM);
        System.out.println(STR_TAX_OTHER_SYSTEM_RESULT + '\t' + '\t' + STR_PAYMENT_SYSTEM);
        System.out.println(STR_SAVINGS_RESULT + '\t' + getSaving()  + '\t' + STR_PAYMENT_SYSTEM);
    }

    public static void setEarnings(Scanner scanner) {
        System.out.println(STR_ENTER_EARNINGS);
        final int input = Integer.parseInt((scanner.nextLine()));
        setEarnings(input);
    }

    public static void setSpending(Scanner scanner) {
        System.out.println(STR_ENTER_SPENDING);
        final int input = Integer.parseInt((scanner.nextLine()));
        setSpending(input);
    }

    public static int taxEarningsMinusSpending() {
        int tax = (getEarnings() - getSpending()) * 15 / 100;
        return Math.max(tax, 0);
    }

    public static int taxEarningsOnly() {
        return getEarnings() * 6 / 100;
    }

    public static void setBestTaxSystem() {
        final int taxEarningsMinusSpending = taxEarningsMinusSpending();
        final int taxEarningsOnly = taxEarningsOnly();
        setBestTaxSystem(taxEarningsOnly < taxEarningsMinusSpending ? STR_TAX_SYSTEM_ONE_RU : STR_TAX_SYSTEM_TWO_RU);
        setTaxEarningsOnly(Math.min(taxEarningsOnly, taxEarningsMinusSpending));
    }

    public static void setSaving() {
        final int taxEarningsMinusSpending = taxEarningsMinusSpending();
        final int taxEarningsOnly = taxEarningsOnly();
        setSaving(Math.abs(taxEarningsOnly-taxEarningsMinusSpending));
    }

    public static int getEarnings() {
        return earnings;
    }

    public static int getSpending() {
        return spending;
    }

    public static void setEarnings(int earning) {
        earnings += earning;
    }

    public static void setSpending(int spe) {
        spending += spe;
    }

    public static int getSaving() {
        return saving;
    }

    public static void setSaving(final int tax) {
        saving = tax;
    }

    public static int getTaxEarningsOnly() {
        return taxEarningsOnly;
    }

    public static void setTaxEarningsOnly(final int taxEarningsOnly) {
        Main.taxEarningsOnly = taxEarningsOnly;
    }

    public static int getTaxEarningsMinusSpending() {
        return taxEarningsMinusSpending;
    }

    public static void setTaxEarningsMinusSpending(final int tax) {
        taxEarningsMinusSpending = tax;
    }

    public static String getBestTaxSystem() {
        return bestTaxSystem;
    }

    public static void setBestTaxSystem(final String system) {
        bestTaxSystem = system;
    }
}
