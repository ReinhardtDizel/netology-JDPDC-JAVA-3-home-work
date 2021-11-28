package ru.netology;

import ru.netology.Player.Player;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        int slot;

        System.out.format("У игрока %d слотов с оружием,"
                        + " введите номер, чтобы выстрелить,"
                        + " -1 чтобы выйти%n",
                player.getSlotsCount()
        );

        while (true) {

            slot = scanner.nextInt();
            if (slot == -1) {

                break;
            }
            player.shotWithWeapon(slot);
        }

        System.out.println("Game over!");
    }
}