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

    public static void main (String[] args) {
        System.out.println("Front: ");
        System.out.println(front[0][0] + front[0][1]);
        System.out.println(front[1][0] + front[1][1]);
        System.out.println("UP: ");
        System.out.println(up[0][0] + up[0][1]);
        System.out.println(up[1][0] + up[1][1]);
        d();
        System.out.println("UP: ");
        System.out.println(up[0][0] + up[0][1]);
        System.out.println(up[1][0] + up[1][1]);
        System.out.println("Front: ");
        System.out.println(front[0][0] + front[0][1]);
        System.out.println(front[1][0] + front[1][1]);
        System.out.println("Down: ");
        System.out.println(down[0][0] + down[0][1]);
        System.out.println(down[1][0] + down[1][1]);
        System.out.println("Back: ");
        System.out.println(back[0][0] + back[0][1]);
        System.out.println(back[1][0] + back[1][1]);
        System.out.println("Left: ");
        System.out.println(left[0][0] + left[0][1]);
        System.out.println(left[1][0] + left[1][1]);
        System.out.println("Right: ");
        System.out.println(right[0][0] + right[0][1]);
        System.out.println(right[1][0] + right[1][1]);
    }

    //Методы поворотов кубика (Язык поворотов кубика стандартный)

    //поворот правой грани на 90 градусов по часовой стрелке
    public static void r() {
        String[] turn = {up[0][1], up[1][1]};
        for (int i = 0; i < 2; i++) {
            up[i][1] = front[i][1];
            front[i][1] = down[i][1];
            down[i][1] = back[i][1];
            back[i][1] = turn[i];
        }
    }

    //поворот верхней грани на 90 градусов по часовой стрелке
    public static void u() {
        String[] turn = {front[0][0], front[0][1]};
        for (int i = 0; i < 2; i++) {
            front[0][i] = right[(i + 1) % 2][0];
            right[(i + 1) % 2][0] = back[1][(i + 1) % 2];
            back[1][(i + 1) % 2] = left[i][1];
            left[i][1] = turn[i];
        }
    }

    //поворот передней грани на 90 градусов по часовой стрелке
    public static void f() {
        String[] turn = {up[1][0], up[1][1]};
        for (int i = 0; i < 2; i++) {
            up[1][i] = left[1][i];
            left[1][i] = down[0][(i + 1) % 2];
            down[0][(i + 1) % 2] = right[1][i];
            right[1][i] = turn[i];
        }
    }

    // поворот левой грани на 90 градусов по часовой стрелке
    public static void l() {
        String[] turn = {up[0][0], up[1][0]};
        for (int i = 0; i < 2; i++) {
            up[i][0] = back[i][0];
            back[i][0] = down[i][0];
            down[i][0] = front[i][0];
            front[i][0] = turn[i];
        }
    }

    // поворот задней грани на 90 градусов по часовой стрелке
    public static void b() {
        String[] turn = {up[0][0], up[0][1]};
        for (int i = 0; i < 2; i++) {
            up[0][i] = right[0][i];
            right[0][i] = down[1][(i + 1) % 2];
            down[1][(i + 1) % 2] = left[0][i];
            left[0][i] = turn[i];
        }
    }

    // поворот нижней грани на 90 градусов по часовой стрелке
    public static void d() {
        String[] turn = {front[1][0], front[1][1]};
        for (int i = 0; i < 2; i++) {
            front[1][i] = left[i][0];
            left[i][0] = back[0][(i + 1) % 2];
            back[0][(i + 1) % 2] = right[(i + 1) % 2][1];
            right[(i + 1) % 2][1] = turn[i];
        }
    }
}