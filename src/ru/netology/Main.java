package ru.netology;


import ru.netology.Service.GameService;

public class Main {

    private static final GameService GAME_SERVICE = new GameService();

    /*
     * ПОБЕДИЛИ КРЕСТИКИ
     */
    private static String[] case1 = {"000--", "XXXXX", "X0X0X", "00-0X", "0-0XX"};
    /*
     * ПОБЕДИЛИ КРЕСТИКИ
     */
    private static String[] case2 = {"X0---", "-X0--", "X-X0-", "00-X-", "0--XX"};
    /*
     * ПОБЕДИЛИ НОЛИКИ
     */
    private static String[] case3 = {"00000", "XXX--", "X-XXX", "0---X", "0----"};
    /*
     * НИКТО НЕ ПОБЕДИЛ
     */
    private static String[] case4 = {"X0X0X", "0X0X0", "--X--", "-----", "-----"};
    /*
     * ПОБЕДИЛИ КРЕСТИКИ
     */
    private static String[] case5 = {"--X--", "-XX-0", "--X-0", "-0X0-", "-0X--"};
    /*
     * ПОБЕДИЛИ КРЕСТИКИ
     */
    private static String[] case6 = {"00--X", "-00X-", "X-X0-", "0X-0-", "X--X0"};

    private static final char[][][] GAME_CASES = GameService.createAreasPack(case1, case2, case3, case4, case5, case5, case6);

    public static void main(String[] args) {

        for (char[][] gameCase : GAME_CASES) {
            GameService.printGameField(gameCase);
            String winner = GameService.whoIsWin(gameCase);
            System.out.println(GameService.gameOver(winner));
        }
    }
}