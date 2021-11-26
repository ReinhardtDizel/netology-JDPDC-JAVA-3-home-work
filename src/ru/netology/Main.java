package ru.netology;

import ru.netology.Service.GameService;

public class Main {
    public static final int SIZE = 5;
    public static final char CROSS = 'X';
    public static final char ZERO = '0';
    /*
     * ПОБЕДИЛИ КРЕСТИКИ
     */
    static String[] testCaseOne = {"000--", "XXXXX", "X0X0X", "00-0X", "0-0XX"};
    /*
     * ПОБЕДИЛИ КРЕСТИКИ
     */
    static String[] testCaseTwo = {"X0---", "-X0--", "X-X0-", "00-X-", "0--XX"};
    /*
     * ПОБЕДИЛИ НОЛИКИ
     */
    static String[] testCaseThree = {"00000", "XXX--", "X-XXX", "0---X", "0----"};
    /*
     * НИКТО НЕ ПОБЕДИЛ
     */
    static String[] testCaseFour = {"X0X0X", "0X0X0", "--X--", "-----", "-----"};
    /*
     * ПОБЕДИЛИ КРЕСТИКИ
     */
    static String[] testCaseFive = {"--X--", "-XX-0", "--X-0", "-0X0-", "-0X--"};
    /*
     * ПОБЕДИЛИ КРЕСТИКИ
     */
    static String[] testCaseSix = {"00--X", "-00X-", "X-X0-", "0X-0-", "X--X0"};
    static char[][][] gameCasesPack = GameService.createFieldsPack(testCaseOne, testCaseTwo, testCaseThree, testCaseFour, testCaseFive, testCaseSix);

    public static void main(String[] args) {

        for (char[][] gameCase : gameCasesPack) {

            GameService.printGameField(gameCase);
            String winner = whoIsWin(gameCase, SIZE);
            System.out.println(gameOver(winner));
        }
    }

    public static String whoIsWin(char[][] field, int count) {

        boolean winCross = isWin(field, CROSS, count);
        boolean winZero = isWin(field, ZERO, count);

        if (winCross) {
            return "Крестики";
        } else if (winZero) {
            return "Нолики";
        } else {
            return "Никто";
        }
    }

    public static boolean isWin(char[][] field, char player, int count) {

        return isWinHorizontal(field, player, count) || isWinVertical(field, player, count) || isWinDiagonals(field, player, count);
    }

    public static boolean isWinHorizontal(char[][] field, char player, int count) {

        for (char[] chars : field) {

            boolean win;
            for (int j = 0; j < field.length || count > 0; j++) {

                win = chars[j] == player;
                if (win) {
                    --count;
                } else {
                    break;
                }
            }
            if (count == 0) {
                return true;
            }

        }
        return false;
    }

    public static boolean isWinVertical(char[][] field, char player, int count) {

        for (int i = 0; i < field.length; i++) {

            boolean win;
            for (int j = 0; j < field.length || count > 0; j++) {

                win = field[j][i] == player;
                if (win) {
                    --count;
                } else {
                    break;
                }
            }
            if (count == 0) {
                return true;
            }

        }
        return false;
    }

    public static boolean isWinDiagonals(char[][] field, char player, int count) {

        boolean win;
        int countToWin = count;
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

        countToWin = count;
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

    public static String gameOver(String winner) {

        String winnerIsStr = "Победили  %s!";
        return String.format(winnerIsStr + "\n", winner);
    }
}