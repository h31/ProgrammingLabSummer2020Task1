import java.util.Arrays;
import java.util.Objects;

public class TicTac {
    // res = X - крестик, 0 - нолик, 1 - пустой
    char[][] field;
    int size;
    char res;

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                string.append(field[i][j]).append("|");
            }
            string.append("\n");
        }
        return string.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicTac ticTac = (TicTac) o;
        return size == ticTac.size &&
                res == ticTac.res &&
                Arrays.equals(field, ticTac.field);
    }

    @Override
    public int hashCode() {
        int resultHash = Objects.hash(size, res);
        resultHash = 31 * resultHash + Arrays.hashCode(field);
        return resultHash;
    }

    public TicTac(int size) {       // поле, заполенное 1 ( 1 - пустой )
        field = new char[size][size];
        this.size = size;
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                field[y][x] = '1';
    }

    public int getValue(int x, int y) { return field[y][x]; }       // возращает элемент ( X ; Y )

    public void set(int x, int y, char res) {       //добавление крестика/нолика в поле
        this.res = res;
        if ((x < 0) || (x > size - 1) || (y < 0) || (y > size - 1)) throw new IllegalArgumentException();
        field[x][y] = res;
    }

    public void delete(int x, int y) { field[x][y] = '1';}     // удаление заданного элемента из поля

    private int horizontal(char res) {
        int count = 0;
        int result = 0;
        int start = 0;
        for (int y = 0; y < size; y++) {
            start = 0;
            for (int x = 0; x < size; x++) {
                int[] answer = check(start, x, y, x, result, count);
                result = answer[0];
                start = answer[1];
                count = answer[2];
                }

        }
        return result;

    }

    private int vertical(char res) {
        int count = 0;
        int result = 0;
        int start = 0;
        for (int x = 0; x < size; x++) {
            start = 0;
            for (int y = 0; y < size; y++) {

                int[] answer = check(start, x, y, y, result, count);
                result = answer[0];
                start = answer[1];
                count = answer[2];
            }
        }
        return result;
    }

    private int diagonal1(char res) {
        int count = 0;
        int result = 0;
        int start = 0;
        for (int x = 0; x < size; x++) {
            start = 0;
            int[] answer = check(start, x, x, x, result, count);
            result = answer[0];
            start = answer[1];
            count = answer[2];

        }
        return result;

    }

    private int diagonal2(char res) {
        int count = 0;
        int result = 0;
        int start = 0;
        int y = 0;
        for (int x = size - 1; x <= 0; x--, y++) {
            start = 0;
            int[] answer = check(start, x, y, y, result, count);
            result = answer[0];
            start = answer[1];
            count = answer[2];
        }
        return result;
    }

    public int search(char res) {           //сравниваю максимальные длины всех направлений между собой
        int horizontal = horizontal(res);
        int vertical = vertical(res);
        int diagonal1 = diagonal1(res);
        int diagonal2 = diagonal2(res);
        int max = -1;

        if (horizontal > max) { max = horizontal;}
        if (diagonal1 > max) { max = diagonal1; }
        if (vertical > max) { max = vertical;}
        if (diagonal2 > max) { max = diagonal2;}
        return max;
    }

    private int[] check(int start, int x, int y, int var, int result, int count) {
        //считаю самую длинную последовательноть для любого направления
        if (field[y][x] == res) {
            if (start == 0) {
                start = 1;
            }
            count++;
            if (var == size - 1) {
                if (count > result) result = count;
                count = 0;
            }
        } else {
            start = 0;
            if (count > result) {
                result = count;
            }
            count = 0;
        }
        return new int[]{result, start, count};
    }
}