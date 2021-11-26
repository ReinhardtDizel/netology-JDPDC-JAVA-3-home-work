package ru.netology.Service;

import java.util.Arrays;

public class GameService {

    public static char[][][] createFieldsPack(String[]... maps) {

        char[][][] pack = new char[maps.length][][];
        for (int i = 0; i < maps.length; ++i) {

            pack[i] = createFieldFromSource(maps[i]);
        }
        return pack;
    }

    public static char[][] createFieldFromSource(String[] sources) {

        final int size = sources.length;
        char[][] res = new char[size][size];
        for (int i = 0; i < size; ++i) {

            res[i] = sources[i].toCharArray();
        }
        return res;
    }

    public static void printGameField(char[][] field) {

        System.out.println();
        for (char[] arr : field) {

            System.out.println(Arrays.toString(arr)
                    .replace("]", "")
                    .replace("[", "")
                    .replace(",", "")
                    .trim()
            );
        }
    }
}