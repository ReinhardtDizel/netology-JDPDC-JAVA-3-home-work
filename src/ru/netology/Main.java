package ru.netology;

import ru.netology.Service.TryToColorService;

import java.util.Random;

public class Main {

    final static int SIZE = 8;
    final static int RANDOM_INT_BOUND = 256;
    final static String MATRIX_CELL_SIZE = "%4d";
    final static String MESSAGE_ORIGIN_MATRIX = "Матрица до поворота:";
    final static String MESSAGE_ROTATED_MATRIX = "Матрица после поворота:";

    public static void main(String[] args) {

        int[][] colors = generateTwoDimensionalArray(SIZE);

        printArray(MESSAGE_ORIGIN_MATRIX, colors, TryToColorService.Color.ANSI_GREEN);

        colors = rotateMatrixRight(colors);

        printArray(MESSAGE_ROTATED_MATRIX, colors, TryToColorService.Color.ANSI_RED);


    }

    public static int[][] generateTwoDimensionalArray(int size) {

        int[][] array = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                array[i][j] = random.nextInt(RANDOM_INT_BOUND);
            }
        }

        return array;
    }

    public static int[][] rotateMatrixRight(int[][] array) {

        final int SIZE = array.length;
        int[][] rotatedResultMatrix = new int[SIZE][SIZE];

        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                rotatedResultMatrix[i][j] = array[array[i].length - j - 1][i];
            }
        }

        return rotatedResultMatrix;
    }

    public static void printArray(String title, int[][] array, TryToColorService.Color color) {

        System.out.println("\n" + title);
        for (int[] ints : array) {

            for (int anInt : ints) {

                String out = String.format(MATRIX_CELL_SIZE, anInt);
                out = TryToColorService.paintThisString(out, color);
                System.out.print(out);
            }

            System.out.println();
        }
    }

}