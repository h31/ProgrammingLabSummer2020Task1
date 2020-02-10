package MyProject;

public class Cube {
    // Цвета для вывода текста
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String YELLOW = "\u001B[33m";
    public static String BLUE = "\u001B[34m";
    public static String PURPLE = "\u001B[35m";
    public static String WHITE = "\u001B[37m";
    String[][] front = {{RED + "▮", RED + "▮"}, {RED + "▮", RED + "▮"}};
    String[][] right = {{BLUE + "▮", BLUE + "▮"}, {BLUE + "▮", BLUE + "▮"}};
    String[][] left = {{GREEN + "▮", GREEN + "▮"}, {GREEN + "▮", GREEN + "▮"}};
    String[][] back = {{PURPLE + "▮", PURPLE + "▮"}, {PURPLE + "▮", PURPLE + "▮"}};
    String[][] up = {{WHITE + "▮", WHITE + "▮"}, {WHITE + "▮", WHITE + "▮"}};
    String[][] down = {{YELLOW + "▮", YELLOW + "▮"}, {YELLOW + "▮", YELLOW + "▮"}};

    public static void main (String[] args) {

    }
}