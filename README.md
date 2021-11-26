# Домашнее задание к занятию "Массивы многомерные"

## Задача 2. Дописываем крестики-нолики

### Описание

Возьмём за основу игру крестики-нолики с вебинара и допишем метод проверки победы одного из игроков, переписав его на
циклы. Так он будет работать при любом значении `SIZE`.

### Образ результата

Победитель определяется методом `whoIsWin(char[][] field)`. Этот метод обрабатывает две переменные типа `boolean`,
которые объявляются в этом методе и инициализируются значением, полученным из
метода `isWin(char[][] field, char player, int count)`. Метод `isWin` обрабатывает результаты трех
методов, которые проверяют истинность условий:
Правда ли, что в двумерном массиве символов, один символ(`char player`) расположен в ряд по `SIZE` раз

1) На одной из строк массива.

2) На одной из колонок массива.

3) на одной из диагональных прямых массива.

Для проверки работоспособности метода `hoIsWin` были определены 6 случаев расстановки символов.

### Пример вывода при запуске main

```
ДЕМОНСТРАЦИЯ

O O O - -
X X X X X
X O X O X
O O - O X
O - O X X
ПОБЕДИЛИ КРЕСТИКИ

X O - - -
- X O - -
X - X O -
O O - X -
O - - X X
ПОБЕДИЛИ КРЕСТИКИ

O O O O O
X X X - -
X - X X X
O - - - X
O - - - -
ПОБЕДИЛИ НОЛИКИ

X O X O X
O X O X O
- - X - -
- - - - -
- - - - -
НИКТО НЕ ПОБЕДИЛ

- - X - -
- X X - 0
- - X - 0
- 0 X 0 -
- 0 X - -
ПОБЕДИЛИ КРЕСТИКИ

0 0 - - X
- 0 0 X -
X - X 0 -
0 X - 0 -
X - - X 0
ПОБЕДИЛИ КРЕСТИКИ
```

### Код игры в крестики-нолики

```java
public class GameDemo {

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
        for (int i = 0; i < field.length || count > 0; i++) {

            win = field[i][i] == player;
            if (win) {
                --count;
            } else {
                break;
            }
        }
        if (count == 0) {
            return true;
        }

        count = SIZE;
        for (int i = 0; i < field.length || count > 0; i++) {

            win = field[field.length - i - 1][i] == player;
            if (win) {
                --count;
            } else {
                break;
            }
        }
        return count == 0;
    }
}
``` 