package MyProject;

import java.util.Arrays;
import java.util.Objects;

enum Colour {

    RED,
    GREEN,
    ORANGE,
    BLUE,
    WHITE,
    YELLOW,
}

public class Cube {

    private Colour[][] front;
    private Colour[][] right;
    private Colour[][] left;
    private Colour[][] back;
    private Colour[][] up;
    private Colour[][] down;
    private int size;
    private final int repeats = 4;

    public Cube(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        front = new Colour[n][n];
        right = new Colour[n][n];
        left = new Colour[n][n];
        back = new Colour[n][n];
        up = new Colour[n][n];
        down = new Colour[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                front[i][j] = Colour.RED;
                right[i][j] = Colour.BLUE;
                left[i][j] = Colour.GREEN;
                back[i][j] = Colour.ORANGE;
                up[i][j] = Colour.WHITE;
                down[i][j] = Colour.YELLOW;
            }
        }
        size = n - 1;
    }


    /**
     * поворот грани, отсчитывая справа на 90 градусов по часовой стрелке
     *
     * @param k - указатель кольца
     */
    public void rightFaceClockwise(int k) {
        if (k > size || k < 0) {
            throw new IllegalArgumentException();
        }
        Colour[] turn = new Colour[size + 1];
        for (int i = 0; i < size + 1; i++) {
            turn[i] = up[i][size - k];
            up[i][size - k] = front[i][size - k];
            front[i][size - k] = down[i][size - k];
            down[i][size - k] = back[i][size - k];
            back[i][size - k] = turn[i];
        }
        if (k == 0) right = rotateClockwise(right);
        else if (k == size) left = rotateCounterClockwise(left);
    }

    /**
     * поворот грани, отсчитывая сверху на 90 градусов по часовой стрелке
     *
     * @param k - указатель кольца
     */
    public void upFaceClockwise(int k) {
        if (k > size || k < 0) {
            throw new IllegalArgumentException();
        }
        Colour[] turn = new Colour[size + 1];
        for (int i = 0; i < size + 1; i++) {
            turn[i] = front[k][i];
            front[k][i] = right[(i + 1) % (size + 1)][k];
            right[(i + 1) % (size + 1)][k] = back[size - k][(i + 1) % (size + 1)];
            back[size - k][(i + 1) % (size + 1)] = left[i][size - k];
            left[i][size - k] = turn[i];
        }
        if (k == 0) up = rotateClockwise(up);
        else if (k == size) down = rotateCounterClockwise(down);
    }

    /**
     * поворот грани, отсчитывая спереди на 90 градусов по часовой стрелке
     *
     * @param k - указатель кольца
     */
    public void frontFaceClockwise(int k) {
        if (k > size || k < 0) {
            throw new IllegalArgumentException();
        }
        Colour[] turn = new Colour[size + 1];
        for (int i = 0; i < size + 1; i++) {
            turn[i] = up[size - k][i];
            up[size - k][i] = left[size - k][i];
            left[size - k][i] = down[k][(i + 1) % (size + 1)];
            down[k][(i + 1) % (size + 1)] = right[size - k][i];
            right[size - k][i] = turn[i];
        }
        if (k == 0) front = rotateClockwise(front);
        else if (k == size) back = rotateCounterClockwise(back);
    }

    /**
     * поворот грани, отсчитывая слева на 90 градусов по часовой стрелке
     *
     * @param k - указатель кольца
     */
    public void leftFaceClockwise(int k) {
        for (int i = 1; i < repeats; i++) {
            rightFaceClockwise(size - k);
        }
    }

    /**
     * поворот грани, отсчитывая сзади на 90 градусов по часовой стрелке
     *
     * @param k - указатель кольца
     */
    public void backFaceClockwise(int k) {
        for (int i = 1; i < repeats; i++) {
            frontFaceClockwise(size - k);
        }
    }

    /**
     * поворот грани, отсчитывая снизу на 90 градусов по часовой стрелке
     *
     * @param k - указатель кольца
     */
    public void downFaceClockwise(int k) {
        for (int i = 1; i < repeats; i++) {
            upFaceClockwise(size - k);
        }
    }


    /**
     * Поворот кубика влево
     */
    public void turnLeft() {
        for (int i = 0; i < size + 1; i++) {
            upFaceClockwise(i);
        }
    }

    /**
     * Поворот кубика вправо
     */
    public void turnRight() {
        for (int i = 0; i < size + 1; i++) {
            downFaceClockwise(i);
        }
    }

    /**
     * Поворот кубика вверх
     */
    public void turnUp() {
        for (int i = 0; i < size + 1; i++) {
            rightFaceClockwise(i);
        }
    }

    /**
     * Поворот кубика вниз
     */
    public void turnDown() {
        for (int i = 0; i < size + 1; i++) {
            leftFaceClockwise(i);
        }
    }

    /**
     * Поворот кубика по часовой стрелке
     */
    public void turnClockwise() {
        for (int i = 0; i < size + 1; i++) {
            frontFaceClockwise(i);
        }
    }

    /**
     * Поворот кубика по часовой стрелке
     */
    public void turnCounterClockwise() {
        for (int i = 0; i < size + 1; i++) {
            backFaceClockwise(i);
        }
    }

    /**
     * запрос состояния фронтальной грани
     *
     * @return возвращает переднюю грань в виде двумерного массива
     */
    public Colour[][] status() {
        return front;
    }

    /**
     * случайная установка состояния кубика
     */
    public void shuffle() {
        int n = (int) (Math.random() * 3);
        for (int i = 1; i < 3 + n; i++) {
            int k = (int) (Math.random() * 3);
            for (int j = 0; j < k; j++) {
                rightFaceClockwise(0);
                downFaceClockwise(0);
            }
            k = (int) (Math.random() * 3);
            for (int j = 0; j < k; j++) {
                backFaceClockwise(0);
                leftFaceClockwise(0);
            }
            k = (int) (Math.random() * 3);
            for (int j = 0; j < k; j++) {
                upFaceClockwise(0);
                frontFaceClockwise(0);
            }
        }
    }

    /**
     * поворот содержимого грани на 90 градусов по часовой стрелке
     */
    private Colour[][] rotateClockwise(Colour[][] face) {
        Colour[][] result = new Colour[face[0].length][face[0].length];
        for (int i = 0; i < face[0].length; i++) {
            for (int j = face[0].length - 1; j >= 0; j--) {
                result[i][face[0].length - 1 - j] = face[j][i];
            }
        }
        return result;
    }

    /**
     * поворот содержимого грани на 90 градусов против часовой стрелке
     */
    private Colour[][] rotateCounterClockwise(Colour[][] face) {
        Colour[][] result = face;
        for (int i = 1; i < repeats; i++) {
            result = rotateClockwise(result);
        }
        return result;
    }

    /**
     * метод, который делает поворот "пиф-паф"
     */
    public void pifPuf() {
        rightFaceClockwise(0);
        upFaceClockwise(0);
        leftFaceClockwise(size);
        downFaceClockwise(size);
    }

    /**
     * метод, который перемещает 2 миникубика между собой
     */
    public void replaceMiniCubes() {
        rightFaceClockwise(0);
        upFaceClockwise(0);
        leftFaceClockwise(size);
        backFaceClockwise(size);
        rightFaceClockwise(0);
        upFaceClockwise(0);
        leftFaceClockwise(size);
        downFaceClockwise(size);
        leftFaceClockwise(size);
        frontFaceClockwise(0);
        rightFaceClockwise(0);
        rightFaceClockwise(0);
        downFaceClockwise(size);
        leftFaceClockwise(size);
        downFaceClockwise(size);
    }

    /**
     * Использованные материалы:
     * 1)https://www.youtube.com/watch?v=AJCW5xTjmTk
     * 2)https://www.youtube.com/watch?v=QUPLE6VuNBM
     * 3)https://www.orientmuseum.ru/museum/23_file_1.pdf
     * поиск и установка миникубиков таким образом
     * миникубики - это составляющие части кубика Рубика. На них на несены цвета, которые составляют грани.
     * в кубике 2x2x2 все миникубики содержат на себе 3 разных цвета.
     * всегда ищется миникубик, содержащий белый цвет и два других, которые задаются через параметры
     * colour1 и colour2 - разные цвета.
     *
     * @param colour1 - цвет, помимо белого, который ищется.
     * @param colour2 - цвет, помимо белого и colour1, который ищется.
     */
    private void placingMiniCube(Colour colour1, Colour colour2) {
        int k = 0;
        if (colour1 == Colour.RED) k = -1;
        boolean[] otherColoursTests = new boolean[2];
        turnUp();
        while (!otherColoursTests[0] && !otherColoursTests[1] && k < 3) {
            turnDown();
            if ((front[0][0] == colour1 || front[0][0] == colour2 || front[0][0] == Colour.WHITE)
                    && (left[1][1] == colour1 || left[1][1] == colour2 || left[1][1] == Colour.WHITE)
                    && (up[1][0] == colour2 || up[1][0] == colour1 || up[1][0] == Colour.WHITE))
                otherColoursTests[0] = true;
            if ((front[0][1] == colour1 || front[0][1] == colour2 || front[0][1] == Colour.WHITE)
                    && (up[1][1] == colour1 || up[1][1] == colour2 || up[1][1] == Colour.WHITE)
                    && (right[1][0] == colour2 || right[1][0] == colour1 || right[1][0] == Colour.WHITE))
                otherColoursTests[1] = true;
            k++;
        }
        for (int i = 1; i < k; i++) {
            turnUp();
            if (otherColoursTests[0]) {
                downFaceClockwise(1);
            } else if (otherColoursTests[1] && colour1 != Colour.BLUE) {
                leftFaceClockwise(1);
            } else if (otherColoursTests[1]) upFaceClockwise(0);
        }
        if (otherColoursTests[0]) {
            downFaceClockwise(1);
            otherColoursTests[0] = false;
        }
        otherColoursTests[1] = false;
    }

    /**
     * решение кубика 2x2x2
     */
    public void solution() {
        int k = 0;
        boolean flag = true;
        //сборка белой стороны и полосы
        for (int i = 1; i < 4; i++) {
            placingMiniCube(Colour.values()[i - 1], Colour.values()[i]);
            while (front[1][1] != Colour.values()[i - 1] || right[1][1] != Colour.values()[i] || down[0][1] != Colour.WHITE) {
                pifPuf();
            }
            turnLeft();
        }
        placingMiniCube(Colour.BLUE, Colour.RED);
        while (front[1][1] != Colour.BLUE || right[1][1] != Colour.RED || down[0][1] != Colour.WHITE) {
            pifPuf();
        }
        //собрал сторону и полосу
        turnClockwise();
        turnClockwise();
        for (int i = 0; i < 4; i++) {
            while (down[0][1] != Colour.YELLOW) {
                pifPuf();
            }
            upFaceClockwise(1);
        }
        turnClockwise();
        turnClockwise();
        //перевернул жёлтую сторону, осталось только поставить одну линию
        while (flag) {
            k++;
            turnRight();
            if (front[0][0] == front[0][1]) {
                while (flag) {
                    downFaceClockwise(0);
                    if (front[0][0] == front[1][0] && front[0][1] == front[1][1]) flag = false;
                }
            }
            if (k > 4) {
                replaceMiniCubes();
                k = 0;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (front[0][0] != front[1][0]) {
                if (front[0][1] != front[1][1]) turnLeft();
                turnRight();
                turnRight();
                replaceMiniCubes();
            } else if (front[0][1] != front[1][1]) replaceMiniCubes();
            turnLeft();
        }
        while (front[0][0] != Colour.RED) turnLeft();
        turnClockwise();
        turnClockwise();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Cube other = (Cube) obj;
        return (Arrays.deepEquals(other.front, front) && Arrays.deepEquals(other.up, up)
                && Arrays.deepEquals(other.back, back) && Arrays.deepEquals(other.down, down)
                && Arrays.deepEquals(other.right, right) && Arrays.deepEquals(other.left, left));

    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(front);
        result = 31 * result + Arrays.hashCode(right);
        result = 31 * result + Arrays.hashCode(left);
        result = 31 * result + Arrays.hashCode(back);
        result = 31 * result + Arrays.hashCode(up);
        result = 31 * result + Arrays.hashCode(down);
        return result;
    }

    @Override
    public String toString() {
        return "Cube{\n" + "Front:\n" + Arrays.deepToString(front) + "\n" +
                "Up:\n" + Arrays.deepToString(up) + "\n" +
                "Back:\n" + Arrays.deepToString(back) + "\n" +
                "Down:\n" + Arrays.deepToString(down) + "\n" +
                "Right:\n" + Arrays.deepToString(right) + "\n" +
                "Left:\n" + Arrays.deepToString(left) + "\n" +
                "}";
    }
}