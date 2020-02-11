package MyProject;

public class Cube {

    private static String[][] front;
    private static String[][] right;
    private static String[][] left;
    private static String[][] back;
    private static String[][] up;
    private static String[][] down;
    private static int size;

    Cube(int n) {
        front = new String[n][n];
        right = new String[n][n];
        left = new String[n][n];
        back = new String[n][n];
        up = new String[n][n];
        down = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                front[i][j] = "\u001B[31m" + "▮"; //Red
                right[i][j] = "\u001B[34m" + "▮"; //Blue
                left[i][j] = "\u001B[32m" + "▮"; //Green
                back[i][j] = "\u001B[35m" + "▮"; //Purple
                up[i][j] = "\u001B[37m" + "▮"; //White
                down[i][j] = "\u001B[33m" + "▮"; //Yellow
            }
        }
        size = n - 1;
    }


    //Методы поворотов кубика (Язык поворотов кубика стандартный), чтобы указать какое "кольцо" надо поворачивать
    //необходимо в метод передать его номер (от 0 до размера кубика - 1)

    //поворот грани, отсчитывая справа на 90 градусов по часовой стрелке
    public static void r(int k) {
        String[] turn = new String[size + 1];
        for (int i = 0; i < size + 1; i++) {
            turn[i] = up[i][size - k];
            up[i][size - k] = front[i][size - k];
            front[i][size - k] = down[i][size - k];
            down[i][size - k] = back[i][size - k];
            back[i][size - k] = turn[i];
        }
        if (k == 0) {
            right = rotate(right);
        } else if (k == size) {
            for (int i = 1; i < 4; i++) {
                left = rotate(left);
            }
        }
    }

    //поворот грани, отсчитывая сверху на 90 градусов по часовой стрелке
    public static void u(int k) {
        String[] turn = new String[size + 1];
        for (int i = 0; i < size + 1; i++) {
            turn[i] = front[k][i];
            front[k][i] = right[(i + 1) % (size + 1)][k];
            right[(i + 1) % (size + 1)][k] = back[size - k][(i + 1) % (size + 1)];
            back[size - k][(i + 1) % (size + 1)] = left[i][size - k];
            left[i][size - k] = turn[i];
        }
        if (k == 0) {
            up = rotate(up);
        } else if (k == size) {
            for (int i = 1; i < 4; i++) {
                down = rotate(down);
            }
        }
    }

    //поворот грани, отсчитывая спереди на 90 градусов по часовой стрелке
    public static void f(int k) {
        String[] turn = new String[size + 1];
        for (int i = 0; i < size + 1; i++) {
            turn[i] = up[size - k][i];
            up[size - k][i] = left[size - k][i];
            left[size - k][i] = down[k][(i + 1) % (size + 1)];
            down[k][(i + 1) % (size + 1)] = right[size - k][i];
            right[size - k][i] = turn[i];
        }
        if (k == 0) {
            front = rotate(front);
        } else if (k == size) {
            for (int i = 1; i < 4; i++) {
                back = rotate(back);
            }
        }
    }

    // поворот грани, отсчитывая слева на 90 градусов по часовой стрелке
    public static void l(int k) {
        for (int i = 1; i < 4; i++) {
            r(size - k);
        }
    }

    // поворот грани, отсчитывая сзади на 90 градусов по часовой стрелке
    public static void b(int k) {
        for (int i = 1; i < 4; i++) {
            f(size - k);
        }
    }

    // поворот грани, отсчитывая снизу на 90 градусов по часовой стрелке
    public static void d(int k) {
        for (int i = 1; i < 4; i++) {
            u(size - k);
        }
    }


    //Повороты кубика
    //Поворот кубика влево
    public static void turnLeft() {
        for (int i = 0; i < size + 1; i++) {
            u(i);
        }
    }

    //Поворот кубика вправо
    public static void turnRight() {
        for (int i = 0; i < size + 1; i++) {
            d(i);
        }
    }

    //Поворот кубика вверх
    public static void turnUp() {
        for (int i = 0; i < size + 1; i++) {
            r(i);
        }
    }

    //Поворот кубика вниз
    public static void turnDown() {
        for (int i = 0; i < size + 1; i++) {
            l(i);
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
                r(0);
                d(0);
            }
            k = (int) (Math.random() * 3);
            for (int j = 0; j < k; j++) {
                b(0);
                l(0);
            }
            k = (int) (Math.random() * 3);
            for (int j = 0; j < k; j++) {
                u(0);
                f(0);
            }
        }
    }

    //поворот содержимого грани на 90 градусов по часовой стрелке
    private static String[][] rotate (String[][] face) {
        String[][] result = new String[face[0].length][face[0].length];
        for (int i = 0; i < face[0].length; i++) {
            for (int j = face[0].length - 1; j >= 0; j--) {
                result[i][face[0].length - 1 - j] = face[j][i];
            }
        }
        return result;
    }
}