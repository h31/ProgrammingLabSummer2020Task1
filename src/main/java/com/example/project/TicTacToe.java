package com.example.project;


import java.util.Arrays;
import java.util.Objects;

public final class TicTacToe {
    private final int size;
    public String[][] desk; // поле хранится в виде двухмерного массива

    public TicTacToe(int size) { // конструктор
        this.size = size;
        this.desk = new String[size][size];
        for (int i = 0; i < size; i++) // заполнение поля по умолчанию
            for (int j = 0; j < size; j++)
                this.desk[i][j] = "*";
    }

    // методы
    public void addCross(int x, int y) { // добавить крестик
        desk[x][y] = "X";
    }

    public void addZero(int x, int y) { // добавить нолик
        desk[x][y] = "O";
    }

    public void cleanCell(int x, int y) { // очистить заданную клетку
        desk[x][y] = "*";
    }

    public String[][] getDesk() { // запрос текущего состояния поля
        return this.desk;
    }

    public String getFieldAt(int x, int y) { // получение конкретного элемента поля по координатам
        return this.desk[x][y];
    }

    public String findVertical(String symbol) {
        int i, j;
        int cnt;
        int max = 0;
        boolean firstCase;
        int startx = -1;
        int starty = -1;
        int maxStartx = -1;
        int maxStarty = -1;
        int endx = -1;
        int endy = -1;
        String str;
        for (j = 0; j < size; j++) {
            cnt = 0;
            firstCase = true;
            for (i = 0; i < size; i++) {
                if (desk[i][j].equals(symbol)) {
                    if (firstCase) {
                        firstCase = false;
                        startx = i;
                        starty = j;
                    }
                    if (i == size - 1) {
                        cnt++;
                        if (cnt > max) {
                            max = cnt;
                            endx = i;
                            endy = j;
                            maxStartx = startx;
                            maxStarty = starty;
                        }
                        cnt = 0;
                    } else cnt++;
                }
                if (!desk[i][j].equals(symbol)) {
                    firstCase = true;
                    if (cnt > max) {
                        max = cnt;
                        endx = i - 1;
                        endy = j;
                        maxStartx = startx;
                        maxStarty = starty;
                    }
                    cnt = 0;
                }
            }
        }
        str = "максимальная длина " + max + " координаты начала (" + maxStartx + ", " + maxStarty + ") "
                + "координаты конца (" + endx + ", " + endy + ")";
        return str;
    }

    public String findHorizontal(String symbol) {
        int i, j;
        int cnt;
        int max = 0;
        boolean firstCase;
        int startx = -1;
        int starty = -1;
        int maxStartx = -1;
        int maxStarty = -1;
        int endx = -1;
        int endy = -1;
        String str;
        for (i = 0; i < size; i++) {
            cnt = 0;
            firstCase = true;
            for (j = 0; j < size; j++) {
                if (desk[i][j].equals(symbol)) {
                    if (firstCase) {
                        firstCase = false;
                        startx = i;
                        starty = j;
                    }
                    if (j == size - 1) {
                        cnt++;
                        if (cnt > max) {
                            max = cnt;
                            endx = i;
                            endy = j;
                            maxStartx = startx;
                            maxStarty = starty;
                        }
                        cnt = 0;
                    } else cnt++;
                }
                if (!desk[i][j].equals(symbol)) {
                    firstCase = true;
                    if (cnt > max) {
                        max = cnt;
                        endx = i; // меняется для горизонтали
                        endy = j - 1;
                        maxStartx = startx;
                        maxStarty = starty;
                    }
                    cnt = 0;
                }
            }
        }
        str = "максимальная длина " + max + " координаты начала (" + maxStartx + ", " + maxStarty + ") "
                + "координаты конца (" + endx + ", " + endy + ")";
        return str;
    }

    public String findDiagonal(String symbol) {
        int i, j;
        int cnt = 0;
        int max1 = 0;
        int max2 = 0;
        int maximum;
        boolean firstCase = true;
        int startx1 = -1;
        int starty1 = -1;
        int startx2 = -1;
        int starty2 = -1;
        int maxStartx1 = -1;
        int maxStarty1 = -1;
        int maxStartx2 = -1;
        int maxStarty2 = -1;
        int maxStartx;
        int maxStarty;
        int endx1 = -1;
        int endy1 = -1;
        int endx2 = -1;
        int endy2 = -1;
        int endx;
        int endy;
        String str;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (i == j) { // поиск по главной диагонали
                    if (desk[i][j].equals(symbol)) {
                        if (firstCase) {
                            firstCase = false;
                            startx1 = i;
                            starty1 = j;
                        }
                        if (j == size - 1) {
                            cnt++;
                            if (cnt > max1) {
                                max1 = cnt;
                                endx1 = i;
                                endy1 = j;
                                maxStartx1 = startx1;
                                maxStarty1 = starty1;
                            }
                            cnt = 0;
                        } else cnt++;
                    }
                    if (!desk[i][j].equals(symbol)) {
                        firstCase = true;
                        if (cnt > max1) {
                            max1 = cnt;
                            endx1 = i - 1; // меняется для горизонтали
                            endy1 = j - 1;
                            maxStartx1 = startx1;
                            maxStarty1 = starty1;
                        }
                        cnt = 0;
                    }
                }
            }
        }
        cnt = 0;
        firstCase = true;

        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (i + j == size - 1) { // поиск по побочной диагонали
                    if (desk[i][j].equals(symbol)) {
                        if (firstCase) {
                            firstCase = false;
                            startx2 = i;
                            starty2 = j;
                        }
                        if (i == size - 1) {
                            cnt++;
                            if (cnt > max2) {
                                max2 = cnt;
                                endx2 = i;
                                endy2 = j;
                                maxStartx2 = startx2;
                                maxStarty2 = starty2;
                            }
                            cnt = 0;
                        } else cnt++;
                    }
                    if (!desk[i][j].equals(symbol)) {
                        firstCase = true;
                        if (cnt > max2) {
                            max2 = cnt;
                            endx2 = i - 1; // меняется для горизонтали
                            endy2 = j + 1;
                            maxStartx2 = startx2;
                            maxStarty2 = starty2;
                        }
                        cnt = 0;
                    }
                }
            }
        }
        maximum = Math.max(max1, max2);
        if (maximum == max1) {
            maxStartx = maxStartx1;
            maxStarty = maxStarty1;
            endx = endx1;
            endy = endy1;
        } else {
            maxStartx = maxStartx2;
            maxStarty = maxStarty2;
            endx = endx2;
            endy = endy2;
        }

        str = "максимальная длина " + maximum + " координаты начала (" + maxStartx + ", " + maxStarty + ") "
                + "координаты конца (" + endx + ", " + endy + ")";
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof TicTacToe) {
            TicTacToe other = (TicTacToe) obj;
            return this.size == other.size &&
                    Arrays.equals(this.desk, other.desk);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(desk);
        return result;
    }
}
