package MyProject;

public class Cube {

    // Цвета для вывода квадратиков
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

    //Повороты кубика
    //Поворот кубика влево
    public static void turnLeft() {
        u();
        for (int i = 1; i < 4; i++) {
            d();
        }
    }

    //Поворот кубика вправо
    public static void turnRight() {
        d();
        for (int i = 1; i < 4; i++) {
            u();
        }
    }

    //Поворот кубика вверх
    public static void turnUp() {
        r();
        for (int i = 1; i < 4; i++) {
            l();
        }
    }

    //Поворот кубика вниз
    public static void turnDown() {
        l();
        for (int i = 1; i < 4; i++) {
            r();
        }
    }

    //запрос состояния грани
    public static String[][] status() {
        return front;
    }

    //случайная установка состояния кубика
    public static void shuffle() {
        int n = (int) (Math.random() * 3);
        for (int i = 1; i < 3 + n; i++) {
            int k = (int) (Math.random() * 3);
            for (int j = 0; j < k; j++) {
                r();
                d();
            }
            k = (int) (Math.random() * 3);
            for (int j = 0; j < k; j++) {
                b();
                l();
            }
            k = (int) (Math.random() * 3);
            for (int j = 0; j < k; j++) {
                u();
                f();
            }
        }
    }
}