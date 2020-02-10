package MyProject;

public class Cube {
    /*
    Для реализации утилиты просто в цикли рандомное число раз поворачиваешь стороны
     */

    // Цвета для вывода текста
    private static String RED = "\u001B[31m";
    private static String GREEN = "\u001B[32m";
    private static String YELLOW = "\u001B[33m";
    private static String BLUE = "\u001B[34m";
    private static String PURPLE = "\u001B[35m";
    private static String WHITE = "\u001B[37m";

    private static String[][] front = {{RED + "▮", RED + "▮"}, {RED + "▮", RED + "▮"}};
    private static String[][] right = {{BLUE + "▮", BLUE + "▮"}, {BLUE + "▮", BLUE + "▮"}};
    private static String[][] left = {{GREEN + "▮", GREEN + "▮"}, {GREEN + "▮", GREEN + "▮"}};
    private static String[][] back = {{PURPLE + "▮", PURPLE + "▮"}, {PURPLE + "▮", PURPLE + "▮"}};
    private static String[][] up = {{WHITE + "▮", WHITE + "▮"}, {WHITE + "▮", WHITE + "▮"}};
    private static String[][] down = {{YELLOW + "▮", YELLOW + "▮"}, {YELLOW + "▮", YELLOW + "▮"}};

    //Методы поворотов кубика (Язык поворотов кубика стандартный)

    public static void r() {
        String[] turn = {up[0][1], up[1][1]};
        for (int i = 0; i < 2; i++) {
            up[i][1] = front[i][1];
            front[i][1] = down[i][1];
            down[i][1] = back[i][1];
            back[i][1] = turn[i];
        }
    }

    public static void u() {
        String[] turn = {front[0][0], front[0][1]};
        for (int i = 0; i < 2; i++) {
            front[0][i] = right[(i + 1) % 2][0];
            right[(i + 1) % 2][0] = back[1][(i + 1) % 2];
            back[1][(i + 1) % 2] = left[i][1];
            left[i][1] = turn[i];
        }
    }
}