package ru.netology;

import java.util.Scanner;
import java.lang.Math;

public class Main {

    public final static String STR_MENU = "Операции над double/float\n1. Сравнить\n2. Округлить\n3. Отбросить дробную часть";
    public final static String STR_ENTER = "Введите ";
    public final static String STR_RESULT = "Результат:";
    public final static String STR_EQUALS_NUMBER = "числа равны";
    public final static String STR_FIRST_NUMBER = "первое число:";
    public final static String STR_SECOND_NUMBER = "второе число:";
    public final static String STR_LAGER = " больше ";
    public final static String STR_LESS = " меньше ";
    public final static String STR_NUMBER = "число";
    public final static String STR_ERROR_INCORRECT_MENU_NUMBER = "Такого функционала пока не завезли!";



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menu = 0;

        System.out.println(STR_MENU);
        System.out.print(STR_ENTER + ':');
        menu = scanner.nextInt();
        switch (menu) {
            case 1:
                compareDoubleFloat(scanner);
                break;
            case 2:
                roundNumber(scanner);
                break;
            case 3:
                removeDecimalPart(scanner);
                break;
            default:
                noMethodInMenu();
                break;
        }
    }

    public static void compareDoubleFloat(Scanner scanner) {
        final double precisionMax = 1E-10;
        final double precisionMin = 1E-4;

        System.out.print(STR_ENTER + STR_FIRST_NUMBER);
        final double a = scanner.nextDouble();
        System.out.print(STR_ENTER + STR_SECOND_NUMBER);
        final float b = scanner.nextFloat();
        final double compareAbs = Math.abs(a - b);

        if (compareAbs >= precisionMax && compareAbs <= precisionMin) {
            System.out.println(STR_RESULT);
            System.out.print(STR_EQUALS_NUMBER);
        } else if (a > b) {
            System.out.println(STR_RESULT);
            System.out.print(a + STR_LAGER + b);
        } else if (a < b) {
            System.out.println(STR_RESULT);
            System.out.print(a + STR_LESS + b);
        } else {
            System.out.print("");
        }
    }

    public static void roundNumber(Scanner scanner) {
        System.out.print(STR_ENTER + STR_NUMBER + ':');
        final double num = scanner.nextDouble();
        final long res = Math.round(num);
        System.out.println(STR_RESULT);
        System.out.print(res);
    }

    public static void removeDecimalPart(Scanner scanner) {
        System.out.print(STR_ENTER + STR_NUMBER + ':');
        final double num = scanner.nextDouble();
        final long res = (long)num;
        System.out.println(STR_RESULT);
        System.out.print(res);
    }

    public static void noMethodInMenu() {
        System.out.println(STR_ERROR_INCORRECT_MENU_NUMBER);
    }
}
