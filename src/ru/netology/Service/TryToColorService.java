package ru.netology.Service;

public class TryToColorService {

    public enum Color {
        ANSI_RESET("\u001B[0m"),
        ANSI_YELLOW("\u001B[33m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_CYAN("\u001B[36m"),
        ANSI_RED("\u001B[31m");

        private String ansiColor;

        Color(String ansiColor) {
            this.ansiColor = ansiColor;
        }

        public String get() {
            return ansiColor;
        }
    }

    private final static String BANNER_STARTER =
            "\n" +
                    " ╦╔╦╗╔═╗╔╦╗╔═╗        ╦╔═╗╦  ╦╔═╗       ╦ ╦ ╦ \n" +
                    " ║ ║║╠═╝ ║║║    ───   ║╠═╣╚╗╔╝╠═╣  ───  ║ ║ ║ \n" +
                    "╚╝═╩╝╩  ═╩╝╚═╝       ╚╝╩ ╩ ╚╝ ╩ ╩       ╩ ╩ ╩ \n";

    public static String getBannerStarter(Color color) {
        return color.get() + BANNER_STARTER + Color.ANSI_RESET.get();
    }

    public static String paintThisString(String string, Color color) {

        return color.get() + string + Color.ANSI_RESET.get();
    }
}
