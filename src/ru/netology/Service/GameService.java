package ru.netology.Service;

import java.util.Scanner;

public class GameService {
    private static final String MATRIX_COL_INDEX_ERROR = "SIZE не может быть больше 16";
    private static final String ENTER_FIELD_SIZE_STR = "Введите размер поля (<= 16)";
    private static final String MATRIX_CELL_SIZE = "%2c";
    private static final String GAME_TITLE = "Крестики-нолики";
    private static final char CROSS = 'X';
    private static final char ZERO = '0';
    private static final String ZERO_STR = "Нолики";
    private static final String CROSS_STR = "Крестики";
    private static final String NOBODY_STR = "Никто";
    private static final String STEP_IS_STR = "Ходят %s!";
    private static final String INCORRECT_CELL = "Сюда ходить нельзя";
    private static final String WINNER_IS_STR = "Победили  %s!";
    private static final String GAME_OVER = "Игра закончена!";
    private static final String SPLITTER = " ";
    private static final char EMPTY = '-';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int size = 5;
    private static char[][] gameField;

    public static String gameOver(String winner) {
        return String.format(WINNER_IS_STR + "\n", winner);
    }

    public static char[][][] createAreasPack(String[]... maps) {
        char[][][] pack = new char[maps.length][][];
        for (int i = 0; i < maps.length; ++i) {
            pack[i] = createFieldFromSource(maps[i]);

        }
        return pack;
    }

    private static char[][] createFieldFromSource(String[] sources) {
        final int size = sources.length;
        char[][] res = new char[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                res[i][j] = sources[i].charAt(j);
            }
        }
        return res;
    }

    public static void printGameField(char[][] field) {

        System.out.println();
        for (char[] chars : field) {
            for (int j = 0; j < field.length; ++j) {

                String out = String.format(MATRIX_CELL_SIZE, chars[j]);
                System.out.print(out);

            }

            System.out.println();
        }
    }

    public static String whoIsWin(char[][] field) {

        boolean winCross = isWin(field, CROSS);
        boolean winZero = isWin(field, ZERO);

        if (winCross) {
            return CROSS_STR;
        } else if (winZero) {
            return ZERO_STR;
        } else {
            return NOBODY_STR;
        }
    }

    private static boolean isWin(char[][] field, char player) {

        return isWinHorizontal(field, player) || isWinVertical(field, player) || isWinDiagonals(field, player);
    }

    private static boolean isWinHorizontal(char[][] field, char player) {

        int countToWin = field.length;
        for (char[] chars : field) {
            boolean win;
            for (int j = 0; j < field.length || countToWin > 0; j++) {
                win = chars[j] == player;

                if (win) {
                    --countToWin;
                } else {
                    break;
                }
            }
            if (countToWin == 0) {
                return true;
            }

        }
        return false;
    }

    private static boolean isWinVertical(char[][] field, char player) {

        int countToWin = field.length;
        for (int i = 0; i < field.length; i++) {
            boolean win;
            for (int j = 0; j < field.length || countToWin > 0; j++) {
                win = field[j][i] == player;

                if (win) {
                    --countToWin;
                } else {
                    break;
                }
            }
            if (countToWin == 0) {
                return true;
            }

        }
        return false;
    }

    private static boolean isWinDiagonals(char[][] field, char player) {

        int countToWin = field.length;
        boolean win;
        for (int i = 0; i < field.length || countToWin > 0; i++) {
            win = field[i][i] == player;
            if (win) {
                --countToWin;
            } else {
                break;
            }
        }
        if (countToWin == 0) {
            return true;
        }

        countToWin = field.length;
        for (int i = 0; i < field.length || countToWin > 0; i++) {
            win = field[field.length - i - 1][i] == player;
            if (win) {
                --countToWin;
            } else {
                break;
            }
        }
        return countToWin == 0;
    }

    public void run() {
    }

}
