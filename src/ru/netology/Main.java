package ru.netology;

import java.util.*;

public class Main {

    private static final int MAX_FLOR = 25;
    private static final Queue<Integer> elevator = new ArrayDeque<>(MAX_FLOR);
    private static final Scanner scanner = new Scanner(System.in);

    private static int totalSeconds = 0;
    private static int previousFloor = -1;

    private static int elevatorProcess() {

        System.out.println("Ожидаю ввода этажа: (для завершения введите 0)");
        int input = scanner.nextInt();
        if (input > MAX_FLOR || input < 0) {
            System.out.println("Такого этажа нет в доме");
        }
        return input;
    }

    private static void calculateTime(int flor) {

        int waitMoveInSeconds = 5;
        int waitDoorsInSeconds = 10;
        if (previousFloor != -1) {
            totalSeconds += Math.abs(flor - previousFloor) * waitMoveInSeconds;
        }
        totalSeconds += waitDoorsInSeconds;
        previousFloor = flor;
    }

    public static void main(String... args) {

        int input = -1;
        while (input != 0) {
            input = elevatorProcess();
            elevator.add(input);
        }
        System.out.println("Лифт проследовал по следующим этажам:");

        while (!elevator.isEmpty()) {
            Integer currentFloor = elevator.poll();
            if (currentFloor != null) {
                calculateTime(currentFloor);
                System.out.printf("этаж %d ", currentFloor);
                if (!elevator.isEmpty()) {
                    System.out.print("-> ");
                }
            }
        }
        System.out.println("\nВремя затраченное лифтом на маршрут =: " + totalSeconds + " с.");
    }
}